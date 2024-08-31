package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructs a deadline task with the specified task name that is not completed by default.
     * The deadline of the task is parsed from the task name.
     *
     * @param taskName The task name.
     */
    public Deadline(String taskName) {
        super(taskName.split("/by")[0]);
        this.deadline = LocalDate.parse(taskName.split("/by")[1].trim());
    }

    /**
     * Constructs a deadline task with the specified task name and completion status.
     *
     * @param taskName The task name.
     * @param isCompleted The deadline.
     */
    public Deadline(String taskName, boolean isCompleted) {
        super(taskName.split("/by")[0], isCompleted);
        this.deadline = LocalDate.parse(taskName.split("/by")[1].trim());
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        return "D " + (super.isCompleted() ? "0" : "1") + " " + this.getTaskName() + "/by " + this.deadline;
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return The deadline of the deadline task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

}
