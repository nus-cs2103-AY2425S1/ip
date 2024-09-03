package Alex.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public LocalDateTime by;

    /**
     * Constructs a Deadline with the given description and deadline time.
     * @param description The description of the deadline task.
     * @param by The deadline time in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (DEADLINE).
     */
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Gets the string representation of the Deadline task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        // Use a more human-readable date format for display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Parses the given date-time string into a LocalDateTime object.
     * @param dateTime The date-time string to parse.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        // Simplified parse logic (ensure this matches how you input dates)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            return LocalDateTime.now(); // Default to now if parsing fails (improve this as needed)
        }
    }
}

