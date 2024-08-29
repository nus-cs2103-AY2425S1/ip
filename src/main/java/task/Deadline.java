package task;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String title, String deadline) {
        this(title, deadline, false);
    }

    public Deadline(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = deadline;
    }

    private String getDeadlineString() {
        return  " (by: " + deadline + ")";
    }

    @Override
    public String storageFormat() {
        return String.format("DEADLINE | %s | %s | %s", super.getStatus(), super.getTitle(), this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDeadlineString();
    }
}
