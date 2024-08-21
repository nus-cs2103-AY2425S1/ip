public class Deadline extends Task {

    public String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return taskIsDone ? "[D][X] " + this.description + " (by: " + deadline.substring(3) + ")" : "[D][ ] " + this.description + " (by: " + deadline.substring(3) + ")";
    }
}
