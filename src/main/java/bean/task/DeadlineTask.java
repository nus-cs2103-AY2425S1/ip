package bean.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a DeadlineTask with the specified name and deadline.
     * The deadline is parsed from the provided string.
     *
     * @param name The name of the task.
     * @param by   The deadline for the task in "yyyy-MM-dd HHmm" format.
     */
    public DeadlineTask(String name, String by) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Retrieves the details of the task, including the deadline in a readable format.
     *
     * @return A string representing the task details.
     */
    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the deadline task to a format suitable for saving to a file.
     *
     * @return A string representing the deadline task in a saveable format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + by.format(formatter);
    }

    /**
     * Returns a string representation of the deadline task, including its type, completion status, name, and details.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + getDetails();
    }
}
