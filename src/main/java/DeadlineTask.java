public class DeadlineTask extends Task {
    private String taskName;
    private DateTime deadline;

    public DeadlineTask(String taskName, String deadline) throws WrongDeadlineFormatException {
        super(taskName);
        this.taskName = taskName;
        this.deadline = new DateTime(deadline);
    }

    public DeadlineTask(String taskName, boolean isCompleted, String deadline) throws WrongDeadlineFormatException {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.deadline = new DateTime(deadline);
    }

    @Override
    public String toFileFormat() {
        return "D | " + (super.isCompleted() ? "1" : "0") + " | "
                + this.taskName + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
