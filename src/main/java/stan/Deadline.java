package stan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws StanInvalidDateTimeFormatException {
        super(description);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String dateTime) throws StanInvalidDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new StanInvalidDateTimeFormatException("The deadline time must be in the format yyyy-MM-dd HHmm.\n"
                    + "E.g. 2021-07-29 2359");
        }
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("D | %s | %s | %s", (isDone ? "1" : "0"), this.description, by.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
