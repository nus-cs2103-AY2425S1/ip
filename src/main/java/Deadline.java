public class Deadline extends Task {
    private String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        setDeadline(deadline);
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    protected String getWriteFormat() {
        return "D , " + (isDone ? "1" : "0") + " , " + name + " , " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
