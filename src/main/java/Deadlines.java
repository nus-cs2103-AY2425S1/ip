public class Deadlines extends Task{

    private final String deadline;

    public Deadlines(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline.trim();
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)","D", super.toString(), this.deadline);
    }
}
