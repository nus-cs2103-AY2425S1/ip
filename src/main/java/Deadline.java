public class Deadline extends Task {
    String deadline;
    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        //return "[D] " + super.toString() + " (by: " + this.deadline + ")";
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
