public class DeadlineTask extends Task {
    private final String dueBy;

    public DeadlineTask(String taskDescription) {
        super(taskDescription);
        this.dueBy = "";
    }

    public DeadlineTask(String taskDescription, String dueBy) {
        super(taskDescription);
        this.dueBy = dueBy;
    }

    private DeadlineTask(boolean isDone, String taskDescription, String dueBy) {
        super(isDone, taskDescription);
        this.dueBy = dueBy;
    }

    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new DeadlineTask(true, super.taskDescription, this.dueBy);
    }

    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new DeadlineTask(super.taskDescription, this.dueBy)
            : this;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
}
