package tasks;

import dateAndTime.ReginaDateAndTime;

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
