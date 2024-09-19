package sentinel.task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task in the Sentinel application.
 * A Deadline task has a description and a specific end date by which the task should be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime endDate;

    /**
     * Constructs a Deadline task with the specified description and end date.
     *
     * @param description The description of the task.
     * @param endDate The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the Deadline task, including the end date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String listedString() {
        return super.listedString() + " [by " + super.localDateTimeToString(this.getEndDate()) + "]";
    }

    /**
     * Returns the end date of the Deadline task.
     *
     * @return The end date of the task.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }
}
