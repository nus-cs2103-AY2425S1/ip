package TaskObj;

public class Deadlines extends Task {
    private final String deadline;
    public Deadlines(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public Deadlines(String desc, String deadline, Boolean isCompleted) {
        super(desc, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toTextString() {
        return "D" + super.toTextString() + " | " + deadline;
    }
}
