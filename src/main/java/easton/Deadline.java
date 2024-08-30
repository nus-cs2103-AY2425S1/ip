package easton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "D,"+ super.getCsvFormat() + "," + by.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm")) + ")";
    }
}
