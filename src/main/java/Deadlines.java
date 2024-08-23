public class Deadlines extends Task {
    private String deadlineOfTask;

    public Deadlines(String description, String deadlineOfTask) {
        super(description);
        this.deadlineOfTask = deadlineOfTask;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + deadlineOfTask + ")";
    }
}