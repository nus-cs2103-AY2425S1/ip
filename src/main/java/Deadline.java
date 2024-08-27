/**
 * This Deadline class extends Task and represents deadlines with a specified end date.
 */

public class Deadline extends Task {

    public String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return taskIsDone ? "[D][X] " + this.description + " (by: " + deadline + ")" : "[D][ ] " + this.description + " (by: " + deadline + ")";
    }
}
