package arts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline. Inherits from the Task class and includes
 * additional information about the due date and time for the task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime by;

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
     * Returns the due date and time for the deadline task.
     *
     * @return The LocalDateTime representing the due date and time.
     */
    public LocalDateTime getBy() {
        return by;
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
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DISPLAY_FORMATTER));
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
        return String.format("D | %s | %s | %s", isDone ? "1" : "0", description, by.format(FILE_FORMATTER));
    }
}
