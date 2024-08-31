public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        type = TaskType.DEADLINE;
    }

    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", getTaskType(),
                super.toString(), deadline);
    }
}
