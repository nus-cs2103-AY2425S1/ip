public class Deadlines extends Task{
    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description + " (by: " + deadline + ")", TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s", isDone ? 1 : 0, description);
    }
}
