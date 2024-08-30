import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDateTime dueDateTime;

    public Deadlines(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        try {
            this.dueDateTime = LocalDateTime.parse(by.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            this.dueDateTime = LocalDateTime.parse(by.trim(), displayFormatter);
        }
    }

    public String getDueDateTime() {
        return dueDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDateTime() + ")";
    }
}
