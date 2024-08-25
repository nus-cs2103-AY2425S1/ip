package tasks;

import dateAndTime.ReginaDateAndTime;

/**
 * Represents a to-do task with a description.
 * This class extends the Task class and provides specific functionality
 * for to-do tasks, indicating whether the task is completed.
 */
public class ToDosTask extends Task {

    /**
     * Constructs a ToDosTask with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDosTask(String description) {
        super(description);
    }

    /**
     * Checks if the to-do task is occurring on the specified date and time.
     * Since to-do tasks do not have specific start or end times, this method
     * will always return true.
     *
     * @param dateAndTime The date and time to check against the task.
     * @return true, as to-do tasks are not time-specific.
     */
    @Override
    public boolean isOccurringOn(ReginaDateAndTime dateAndTime) {
        return true;
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving.
     * The format includes the task type, completion status, and description.
     *
     * @return A formatted string representing the to-do task for saving purposes.
     */
    @Override
    public String toSavedFormatting() {
        return String.format("T | %s | %s",
                this.isDone ? "X" : " ",
                this.description);
    }

    /**
     * Returns a string representation of the to-do task, including its
     * status (done or not) and description.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
