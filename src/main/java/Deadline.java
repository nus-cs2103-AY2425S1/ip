public class Deadline extends Task {
    private final static String type = "[D]";
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + deadline + ")";
    }
}
