package fred.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and includes a due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    DateTimeFormatter formatter;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description,
     * and due date.
     *
     * @return A string in the format "[D][status] description (by: due date)".
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for data storage.
     *
     * @return A string in the format "D | status | description | due date".
     */
    @Override
    public String getDataFormat() {
        return "D" + super.getDataFormat() + " | " + by.format(formatter);
    }
}
