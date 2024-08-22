public class Deadline extends Task {
    String deadline;
    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    public static Deadline createDeadline(String input) throws EmptyDescriptionException {
        String deadlineDescription = input.substring(8).trim();
        if (deadlineDescription.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String deadlineName = deadlineDescription.split(" /by ")[0];
        String deadlineDay = deadlineDescription.split(" /by ")[1];
        return new Deadline(deadlineName, deadlineDay);
    }

    @Override
    public String toString() {
        //return "[D] " + super.toString() + " (by: " + this.deadline + ")";
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
