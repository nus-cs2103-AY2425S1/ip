package monique.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The <code>Deadline</code> class represents a task with a specific deadline.
 * It extends the <code>Task</code> class and includes a due date for the task.
 */
public class Deadline extends Task {
    public static final DateTimeFormatter DATE_TIME_FORMAT_DATE_ONLY = DateTimeFormatter.ofPattern("MMM d yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMAT_DATE_AND_TIME =
            DateTimeFormatter.ofPattern("MMM d yyyy h:[mm]a");
    private static final String FORMAT_STRING = "[D][%s] %s (by: %s)";
    private final LocalDateTime by;
    private final boolean hasTime;


    /**
     * Constructs a new <code>Deadline</code> object with the specified description,
     * completion status, and due date.
     *
     * @param description The description of the task.
     * @param isComplete The completion status of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, boolean isComplete, LocalDateTime by, boolean hasTime) {
        super(description , isComplete);
        this.by = by;
        this.hasTime = hasTime;
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
                    this.getDescription() ,
                    this.hasTime ? this.by.format(DATE_TIME_FORMAT_DATE_AND_TIME)
                            : this.by.format(DATE_TIME_FORMAT_DATE_ONLY));
    }

    /**
     * Marks the <code>Deadline</code> task as complete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as complete.
     */
    @Override
    public Deadline mark() {
        return new Deadline(this.getDescription(), true, this.by, this.hasTime);
    }

    /**
     * Unmarks the <code>Deadline</code> task as incomplete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as incomplete.
     */
    @Override
    public Deadline unmark() {
        return new Deadline(this.getDescription(), false, this.by, this.hasTime);
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
