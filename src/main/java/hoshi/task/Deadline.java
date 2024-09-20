package hoshi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a Deadline with a description, completion status and end time.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of the deadline, whether the task has been completed or not and the end time.</p>
 */
public class Deadline extends Task {

    /**
     * Indicates when the deadline is due to end.
     */
    private final LocalDate endDate;

    /**
     * Constructs a new instance of Deadline.
     *
     * @param description String description of Deadline.
     * @param endDate LocalDateTime end date of Deadline.
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Deadline.
     * @param isDone Boolean indicating whether Deadline is done or not.
     * @param endDate LocalDateTime end date of Deadline.
     */
    public Deadline(String description, Boolean isDone, LocalDate endDate) {
        super(description, isDone);
        this.endDate = endDate;
    }

    /**
     * Gets end date of the Deadline.
     *
     * @return description endDate which indicates when the Deadline is due by.
     */
    public LocalDate getEndTime() {
        return this.endDate;
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * <p>The string includes the status icon and the description of the Deadline.</p>
     *
     * @return A string in the format {"[statusIcon] description"}.
     */
    @Override
    public String toString() {
        // format datetime before printing
        String endTime = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }

}
