import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String name, boolean isDone, String deadline) {
        super(name,isDone);
        this.deadline = LocalDateTime.parse(deadline);
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.check() + "] " + this.name + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
