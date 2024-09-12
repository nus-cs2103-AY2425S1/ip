package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Encapsulates the description of a task, with its deadline.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Initialises a Deadline object.
     *
     * @param description description of the task.
     * @param deadline due date of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Initialises a Deadline object.
     *
     * @param description description of the task.
     * @param deadline due date of the task.
     * @param priority priority of the task.
     */
    public Deadline(String description, String deadline, String priority) {
        super(description, priority);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the string of the task to be saved to data file.
     *
     * @return string representing the task information.
     */
    @Override
    public String writeTask() {
        return super.writeTask() + "," + this.deadline;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string of task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
