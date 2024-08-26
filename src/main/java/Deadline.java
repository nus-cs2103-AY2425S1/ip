import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String formattedByDate = (this.byDate != null)
                ? this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.by;
        return "[D]" + super.toString() + " (by: " + formattedByDate + ")";
    }

    @Override
    public List<String> getTaskDetails() {
        String formattedByDate = (this.byDate != null)
                ? this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.by;
        return List.of("D", getStatus(), description, formattedByDate);
    }
}
