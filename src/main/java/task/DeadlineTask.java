package task;

public class DeadlineTask extends Task {
    private final String deadline;
    public DeadlineTask(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }
}
