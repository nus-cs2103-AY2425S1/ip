package bob.task;

import bob.exception.LineCorruptedException;
import bob.util.DateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static bob.Storage.DATE_TIME_FORMATTER;

public class Deadline extends Task {
    public static final char ENCODED_LETTER = 'D';
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by, String... tags) {
        super(description, tags);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String encode() {
        // format: <isDone><len(desc)#4><desc><by#12><tag tag ...>
        StringBuilder str = new StringBuilder();

        str.append(this.isDone ? 1 : 0);
        str.append(String.format("%04d", this.description.length()));
        str.append(this.description);

        String formattedBy = by.format(DATE_TIME_FORMATTER);
        str.append(formattedBy);

        String tagsAsString = this.tags.stream().reduce("", (s, tag) -> s + " " + tag).trim();
        str.append(tagsAsString);

        return str.toString();
    }

    public static Task decode(String encodedString) throws LineCorruptedException {
        // format: <isDone><len(desc)#4><desc><by#12><tag tag ...>
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
        String by = encodedString.substring(n, n + 12);
        LocalDateTime parsedBy = LocalDateTime.from(DATE_TIME_FORMATTER.parse(by));

        String tagsAsString = encodedString.substring(n + 12);
        String[] tags = Arrays.stream(tagsAsString.split(" "))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        return new Deadline(desc, parsedBy, tags);
    }

    @Override
    public String toString() {
        String formattedBy = DateTime.format(by);
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}