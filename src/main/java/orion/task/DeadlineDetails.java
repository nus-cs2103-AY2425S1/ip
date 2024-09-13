package orion.task;

import java.time.LocalDateTime;

/**
 * A simple data carrier class that holds the details of a deadline.
 *
 * <p>This class is used to transfer deadline information, including
 * the task's description and due date, between different parts of the
 * program. The fields are private and final, ensuring that the data
 * is immutable once an instance of the class is created.</p>
 *
 * <p>The class provides getter methods for accessing the deadline details.</p>
 */
public class DeadlineDetails {
    private final String description;
    private final LocalDateTime by;

    /**
     * Constructs a DeadlineDetails object with the specified description
     * and due date.
     *
     * @param description a brief description of the task with a deadline
     * @param by the due date and time of the deadline
     */
    public DeadlineDetails(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return the description of the deadline task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the due date and time of the deadline.
     *
     * @return the due date and time of the deadline
     */
    public LocalDateTime getBy() {
        return by;
    }
}
