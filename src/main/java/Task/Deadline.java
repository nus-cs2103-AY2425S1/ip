package task;

public class Deadline extends Task{
    private final String by;

    public Deadline(int taskId, String description, String by) {
        super(taskId, description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }


}
