package alice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class and includes a specific due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline for the task, represented as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the specified description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline for the task, represented as a LocalDateTime object.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the task suitable for saving to storage.
     * The format includes a task type identifier, the task's description, completion status, and the deadline.
     *
     * @return A string representing the task in a saveable format.
     */
    @Override
    public String saveString() {
        DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedTime = (by != null) ? by.format(saveFormatter) : "N/A";
        return "D" + super.saveString() + " | " + formattedTime;
    }

    /**
     * Returns a string representation of the Deadline task for display.
     * Includes the task's description and deadline formatted for readability.
     *
     * @return A string representing the task, including its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedTime = (by != null) ? by.format(formatter)
                : "Fail to set deadline, check time format: dd-MM-yyyy HHmm";
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
