package bob.task;

import bob.exception.LineCorruptedException;
import bob.exception.WrongTaskException;
import bob.util.DateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static bob.Storage.DATE_TIME_FORMATTER;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String encode() {
        // format: D<isDone><len(desc)#4><desc><by>
        StringBuilder str = new StringBuilder();

        str.append("D");
        str.append(this.isDone ? 1 : 0);
        str.append(String.format("%04d", this.description.length()));
        str.append(this.description);

        String formattedBy = by.format(DATE_TIME_FORMATTER);
        str.append(formattedBy);

        return str.toString();
    }

    public static Task decode(String encodedString) throws WrongTaskException, LineCorruptedException {
        // format: D<isDone><len(desc)#4><desc><by>
        if (encodedString.charAt(0) != 'D') {
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

        return task;
    }

    private static Task getTask(String encodedString) {
        int descLength = Integer.parseInt(encodedString.substring(2, 6));
        String desc = encodedString.substring(6, 6 + descLength);
        String by = encodedString.substring(6 + descLength);
        LocalDateTime parsedBy = LocalDateTime.from(DATE_TIME_FORMATTER.parse(by));

        return new Deadline(desc, parsedBy);
    }

    @Override
    public String toString() {
        String formattedBy = DateTime.format(by);
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}