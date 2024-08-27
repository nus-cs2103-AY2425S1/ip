import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(formatter));
    }
}
