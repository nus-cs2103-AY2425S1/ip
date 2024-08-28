public class Deadlines extends Task {
    private String deadlineOfTask;

    public Deadlines(String description, String deadlineOfTask) {
        super(description);
        this.deadlineOfTask = deadlineOfTask;
    }

    public Deadlines(String description, String deadlineOfTask, String done) {
        super(description);
        this.deadlineOfTask = deadlineOfTask;
        if (done.equals("1")) {super.setDone();}
    }

    public String getDeadlineOfTask() {
        return deadlineOfTask;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + deadlineOfTask + ")";
    }
}