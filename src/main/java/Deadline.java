public class Deadline extends Task {
    private final String deadline;
    Deadline(String desc, String deadline) throws EmptyDescException {
        super(desc);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
