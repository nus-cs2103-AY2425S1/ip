public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }
    public String toFileString() {
        return "T | " + super.toFileString() + " | " + deadline;
    }
}
