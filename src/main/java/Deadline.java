public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.deadline + ")"; 
    }
}
