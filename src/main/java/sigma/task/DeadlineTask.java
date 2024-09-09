package sigma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime date;

    /**
     * Creates a deadline task with the given description and date.
     *
     * @param description Description of the deadline task.
     * @param date Date of the deadline task.
     */
    public DeadlineTask(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the date of the deadline task in MMM d yyyy, HH:mm format.
     *
     * @return Date of the deadline task.
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return date.format(formatter);
    }

    /**
     * Returns the type of the task.
     *
     * @return String representation of type of the task.
     */
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusString(), getDescription(), getDate());
    }

}
