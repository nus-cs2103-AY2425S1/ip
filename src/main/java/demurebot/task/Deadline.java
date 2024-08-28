package demurebot.task;

/**
 * Represents a Deadline task in the DemureBot application.
 * A Deadline task has a description, a deadline (by), and a status indicating whether it is done.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline task with the specified description, deadline, and status.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task.
     * @param isDone The status of the Deadline task, true if the task is done, false otherwise.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

