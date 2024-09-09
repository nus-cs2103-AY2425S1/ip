package monique.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The <code>Deadline</code> class represents a task with a specific deadline.
 * It extends the <code>Task</code> class and includes a due date for the task.
 */
public class Deadline extends Task {

    private static final String FORMAT_STRING = "[D][%s] %s (by: %s)";
    private final LocalDateTime by;

    /**
     * Constructs a new <code>Deadline</code> object with the specified description,
     * completion status, and due date.
     *
     * @param description The description of the task.
     * @param isComplete The completion status of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, boolean isComplete, LocalDateTime by) {
        super(description , isComplete);
        this.by = by;
    }
    /**
     * Returns a string representation of the <code>Deadline</code> task.
     * The format is: "[D][status] description (by: date)" where status is "X" if the task is complete,
     * and the date is formatted as "MMM d yyyy".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.isComplete() ? "X" : " ",
                    this.getDescription() , this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Marks the <code>Deadline</code> task as complete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as complete.
     */
    @Override
    public Deadline mark() {
        return new Deadline(this.getDescription(), true, this.by);
    }

    /**
     * Unmarks the <code>Deadline</code> task as incomplete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as incomplete.
     */
    @Override
    public Deadline unmark() {
        return new Deadline(this.getDescription(), false, this.by);
    }


    /**
     * Returns the due date of the <code>Deadline</code> task.
     *
     * @return The due date of the task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }


}
