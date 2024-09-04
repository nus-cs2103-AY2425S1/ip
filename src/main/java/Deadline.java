import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime date;
    public Deadline(String description, String date) throws BuzzException {
        super(description);
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.date = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BuzzException("WRONG!!! The date and time format should be d/M/yyyy HHmm (e.g., 5/10/2024 0500).");
        }
    }
    public String toFileFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.date.format(dateTimeFormatter);
    }
    public String formatBy() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.date.format(dateTimeFormatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }
}
