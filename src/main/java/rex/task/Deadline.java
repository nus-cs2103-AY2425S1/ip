package rex.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a task that has a specific deadline.
 * It extends the {@code Task} class by adding a deadline date and time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task with the specified description, completion status, and deadline.
     *
     * @param description The description of the Deadline task.
     * @param isDone      The completion status of the Deadline task.
     * @param by          The deadline by which the task should be completed.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type identifier "[D]",
     * the task details from the {@code Task} superclass, and the deadline formatted as "dd MMM yyyy HHmm".
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[D]" + super.toString() + "(by: " + by.format(outputFormat) + "HRS)";
    }

    /**
     * Returns a formatted string suitable for saving to a file. The format includes a type identifier "D",
     * followed by the formatted output from the {@code Task} superclass, and the deadline formatted as "dd-MM-yy HHmm".
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String formatter() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
        return "D | " + super.formatter() + " | " + by.format(fileFormat);
    }
}
