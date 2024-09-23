package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific date and time by which the task must be completed.
 * Extends {@link Task}.
 */
public class Deadline extends Task {
    /**
     * The date and time by which the task must be completed.
     */
    private LocalDateTime by;

    /**
     * Constructs a new Deadline with the default priority (low).
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the deadline task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline with a specific priority.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the deadline task must be completed.
     * @param priority The priority level (1 for high, 2 for medium, 3 for low).
     */
    public Deadline(String description, LocalDateTime by, int priority) {
        super(description);
        this.by = by;
        setPriority(priority); // Set the priority using the method from the superclass.
    }

    /**
     * Returns a string representation of the deadline, including its description,
     * its due date formatted as "MMM d yyyy h:mma", and the priority.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    /**
     * Returns a string suitable for file storage, representing the deadline task with its completion status,
     * description, due date formatted as "d/M/yyyy HHmm", and priority.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s | %d",
                (isDone ? 1 : 0),
                description,
                by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                getPriority());
    }
}
