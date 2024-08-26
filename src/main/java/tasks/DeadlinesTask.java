package tasks;

import dateAndTime.ReginaDateAndTime;
import exception.ReginaException;

/**
 * Represents a deadline task with a description and a specific deadline.
 * This class extends the Task.Task class and provides additional functionality
 * for deadline tasks.
 */
public class DeadlinesTask extends Task {
    protected ReginaDateAndTime deadline;

    /**
     * Constructs a tasks.DeadlinesTask with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline associated with the task.
     */
    public DeadlinesTask(String description, String deadline) throws ReginaException {
        super(description);
        this.deadline = new ReginaDateAndTime(deadline);
    }

    /**
     * Checks if the deadline for this task is occurring at the specified date and time.
     *
     * This method determines if the provided date and time is equal to or before
     * the deadline of the task. It considers the deadline to be occurring if
     * the specified date and time is at or before the deadline.
     *
     * @param dateAndTime The date and time to check against the task's deadline.
     * @return true if the specified date and time is on or before the deadline,
     *         false otherwise.
     */
    @Override
    public boolean isOccurringOn(ReginaDateAndTime dateAndTime) {
        return this.deadline.isAfter(dateAndTime) || this.deadline.isEqual(dateAndTime);
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving.
     * The format includes the task type, completion status, description and deadline.
     *
     * @return A formatted string representing the event task for saving purposes.
     */
    @Override
    public String toSavedFormatting() {
        return String.format("D | %s | %s | %s",
                this.isDone ? "X" : " ",
                this.description,
                this.deadline.toSavedFormatting());
    }

    /**
     * Returns a string representation of the deadline task, including its
     * status (done or not), description, and deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline.toString());
    }
}
