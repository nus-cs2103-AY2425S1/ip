package arts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline. Inherits from the Task class and includes
 * additional information about the due date and time for the task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert by != null : "Due date cannot be null";

        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its type,
     * description, and formatted due date.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        assert by != null : "Due date should not be null when formatting";

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    /**
     * Returns a string representation of the deadline task formatted for file storage.
     * This includes the task type, completion status, description, and due date.
     *
     * @return A string formatted for storing the deadline task in a file.
     */
    @Override
    public String toFileFormat() {
        assert by != null : "Due date should not be null when formatting for file";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
