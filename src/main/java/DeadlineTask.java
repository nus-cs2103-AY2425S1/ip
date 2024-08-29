public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskItem, String deadline, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toDatabaseFormat() {
        return "D | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskItem()  + " | " + this.deadline;
    }
}
