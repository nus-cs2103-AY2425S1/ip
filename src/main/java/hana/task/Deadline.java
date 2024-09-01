package hana.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    public String by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its status and due date.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
