import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String dateTime) {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(dateTime.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'd/M/yyyy HHmm'.");
            throw e;  // Re-throw the exception after logging it
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}