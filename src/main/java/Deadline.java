public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}