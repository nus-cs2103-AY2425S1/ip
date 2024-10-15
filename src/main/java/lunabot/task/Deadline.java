package lunabot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the task list.
 * A Deadline task has a description, completion status (whether it is done or not),
 * and the due date of the task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a Deadline task with specified description, deadline and completion status.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task represented as LocalDateTime.
     * @param isDone Whether the Deadline task is completed or not.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }


    /**
     * Constructs a Deadline task with specified description, deadline and completion status set to not done.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task represented as LocalDateTime.
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    /**
     * Converts the Deadline task into a format suitable for saving to a file.
     *
     * @return A string representation of the Deadline task in the format to be stored in the file.
     *         The format is "D | 1 or 0 (done or not done) | description | yyyy-MM-dd HH:mm (deadline)".
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Returns the string representation of the Deadline task, including its description, completion status
     * and deadline.
     *
     * @return A string representation of the Deadline task.
     *         The format is "[D][X or ] description (by: MMM dd yyyy, h:mm a)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}

