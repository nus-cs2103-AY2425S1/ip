import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + "(by: " + getDeadline() + ")";
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }

}
