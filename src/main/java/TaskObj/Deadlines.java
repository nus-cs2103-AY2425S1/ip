package TaskObj;

public class Deadlines extends Task {
    private final String deadline;
    public Deadlines(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }
}
