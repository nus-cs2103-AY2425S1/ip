import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm") );
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "Deadline | " + (isDone ? "Done" : "Not Done") + " | " + description + " | " +  by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}
