public class Deadlines extends Task {
    private String deadlineInfo;

    Deadlines(String name, String details) {
        super(name);
        this.deadlineInfo = details;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (%s)", deadlineInfo);
    }
}
