package interfaces;

/**
 * Represents a deadline task, which is a specific type of Task with a due date.
 * This class extends the Task class and adds functionality specific to deadlines.
 */
public class Deadlines extends Task {
    /**
     * The due date of the deadline task.
     */
    protected String by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task. The "deadline " prefix will be removed if present.
     * @param by          The due date of the deadline task.
     */
    public Deadlines(String description, String by) {
        super(description.replace("deadline ", ""));
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format "[D][x] description (by: due-date)" where x is replaced with " " if the task is not done.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving it into a file.
     *
     * @return A string in the format "deadline description /by due-date | isDone | tag"
     */
    @Override
    public String loadString() {
        return "deadline " + this.description + " /by " + this.by + " | " + this.isDone + " | " + this.tag;
    }
}