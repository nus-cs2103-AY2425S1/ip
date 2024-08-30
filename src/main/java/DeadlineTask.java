public class DeadlineTask extends Task {
    protected String by;
    public DeadlineTask(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getTaskName() + " | " + by;
    }
}
