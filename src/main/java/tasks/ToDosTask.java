package tasks;

import dateandtime.ReginaDateAndTime;

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
     * Tells the user that this task type does not need to be snoozed.
     *
     * @param durationType A string representing the type duration in days, hours or minutes.
     * @param durationValue An int representing the value of the specified type to push back the deadline.
     *
     * @return A string representing the message to the user of the action.
     */
    @Override
    public String snoozeTask(String durationType, int durationValue) {
        return "The task, "
                + this.description
                + ". has no deadline lah. You can do whenever you want, don't need to snooze.";
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
