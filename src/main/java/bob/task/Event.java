package bob.task;

import bob.exception.LineCorruptedException;
import bob.util.DateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static bob.Storage.DATE_TIME_FORMATTER;

public class Event extends Task {
    public static final char ENCODED_LETTER = 'E';
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, String... tags) {
        super(description, tags);
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
        // format: <isDone><len(desc)#4><desc><from#12><to#12><tag tag ...>
        StringBuilder str = new StringBuilder();

        str.append(this.isDone ? 1 : 0);
        str.append(String.format("%04d", this.description.length()));
        str.append(this.description);

        String formattedFrom = from.format(DATE_TIME_FORMATTER);
        str.append(formattedFrom);

        String formattedTo = to.format(DATE_TIME_FORMATTER);
        str.append(formattedTo);

        String tagsAsString = this.tags.stream().reduce("", (s, tag) -> s + " " + tag).trim();
        str.append(tagsAsString);

        return str.toString();
    }

    public static Task decode(String encodedString) throws LineCorruptedException {
        // format: <isDone><len(desc)#4><desc><from#12><to#12><tag tag ...>
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

        return task;
    }

    private static Task getTask(String encodedString) {
        int descLength = Integer.parseInt(encodedString.substring(1, 5));
        int n = 5 + descLength;
        String desc = encodedString.substring(5, n);

        // Length of datetime is 12
        String from = encodedString.substring(n, n + 12);
        String to = encodedString.substring(n + 12, n + 24);

        LocalDateTime parsedFrom = LocalDateTime.from(DATE_TIME_FORMATTER.parse(from));
        LocalDateTime parsedTo = LocalDateTime.from(DATE_TIME_FORMATTER.parse(to));

        String tagsAsString = encodedString.substring(n + 24);
        String[] tags = Arrays.stream(tagsAsString.split(" "))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        return new Event(desc, parsedFrom, parsedTo, tags);
    }

    @Override
    public String toString() {
        String formattedFrom = DateTime.format(from);
        String formattedTo = DateTime.format(to);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
