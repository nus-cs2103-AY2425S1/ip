public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskItem, String deadline) {
        super(taskItem);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
