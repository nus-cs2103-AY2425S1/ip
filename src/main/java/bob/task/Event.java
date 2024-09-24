package bob.task;

import bob.exception.InvalidDateTimeException;
import bob.exception.LineCorruptedException;
import bob.exception.WrongTaskException;
import bob.util.DateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static bob.Storage.DATE_TIME_FORMATTER;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String encode() {
        // format: E<isDone><len(desc)#4><desc><len(from)#4><from><to>
        StringBuilder str = new StringBuilder();

        str.append("E");
        str.append(this.isDone ? 1 : 0);
        str.append(String.format("%04d", this.description.length()));
        str.append(this.description);

        String formattedFrom = from.format(DATE_TIME_FORMATTER);
        str.append(String.format("%04d", formattedFrom.length()));
        str.append(formattedFrom);

        String formattedTo = to.format(DATE_TIME_FORMATTER);
        str.append(formattedTo);

        return str.toString();
    }

    @Override
    public Task decode(String encodedString) throws WrongTaskException, LineCorruptedException {
        // format: E<isDone><len(desc)#4><desc><len(from)#4><from><to>
        if (encodedString.charAt(0) != 'E') {
            throw new WrongTaskException();
        }

        Task task;
        try {
            task = getTask(encodedString);
        } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeException e) {
            throw new LineCorruptedException();
        }

        if (encodedString.charAt(1) == '1') {
            task.mark();
        } else if (encodedString.charAt(1) != '0') {
            throw new LineCorruptedException();
        }

        return null;
    }

    private Task getTask(String encodedString) {
        int descLength = Integer.parseInt(encodedString.substring(2, 6));
        int curr = 6 + descLength;
        String desc = encodedString.substring(6, curr);
        int fromLength = Integer.parseInt(encodedString.substring(curr, curr + 4));

        curr += 4;
        String from = encodedString.substring(curr, curr + fromLength);
        String to = encodedString.substring(curr + fromLength);

        LocalDateTime parsedFrom = LocalDateTime.from(DATE_TIME_FORMATTER.parse(from));
        LocalDateTime parsedTo = LocalDateTime.from(DATE_TIME_FORMATTER.parse(to));

        return new Event(desc, parsedFrom, parsedTo);
    }

    @Override
    public String toString() {
        String formattedFrom = DateTime.format(from);
        String formattedTo = DateTime.format(to);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
