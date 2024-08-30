package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline, containing a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task, including its description and due date.
     *
     * @return A formatted string representing the task and its due date.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the formatted string representation of the task to be saved in storage.
     *
     * @return A formatted string for saving the task to a file.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.toString();
    }

    /**
     * Gets the due date of the task.
     *
     * @return The due date of the task as a LocalDate object.
     */
    public LocalDate getByDate() {
        return by;
    }
}