import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.from(this.by);
    }

    public String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(this.by) + ")";
    }
}