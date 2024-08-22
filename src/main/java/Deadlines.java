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
}
