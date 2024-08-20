import java.time.LocalDateTime;

public class Deadline extends Task{
    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }
}
