public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public DeadlineTask(String desc, String deadline, boolean isDone) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }
}
