public class Deadline extends Task {
    private final String deadline;
    Deadline(String desc, String deadline) throws EmptyDescException {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String getOriginalCommand() {
        return "deadline " + super.getOriginalCommand() + " /by " + this.deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
