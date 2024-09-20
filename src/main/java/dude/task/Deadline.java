package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in Dude.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and deadline.
     * Date and time must be in specific format: "yyyy-MM-dd HH:mm"
     *
     * @param description The description of the task.
     * @param by The date and time represent the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task's data into a string format for saving to a file.
     *
     * @return A string representing the Deadline task's data for saving.
     */
    @Override
    public String taskToStringData() {
        return "D" + super.taskToStringData() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this Deadline object.
     *
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        boolean isDeadline = obj instanceof Deadline;
        boolean isSameDescription = super.equals(obj);
        if (!isDeadline || !isSameDescription) {
            return false;
        }

        Deadline e = (Deadline) obj;
        return this.by.equals(e.by);
    }
}
