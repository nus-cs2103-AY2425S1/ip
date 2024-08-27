import java.time.LocalDateTime;

public class Deadline extends Task{
    protected String deadline;

    Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toFileFormat() {
        String isDoneString = this.isDone ? "1" : "0";
        return getTypeIcon().charAt(1) + "|" + isDoneString + "|" +
                this.getDescription() + "|" + this.getDeadline();
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
