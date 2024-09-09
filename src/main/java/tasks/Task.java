package tasks;

import dateandtime.ReginaDateAndTime;
import errorhandling.ReginaException;

/**
 * Represents a task with a description and status (done or not done).
 * This is an abstract class to be extended by specific task types
 * such as ToDos, Deadlines, and Events.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task.Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to the saved data file.
     * The exact format will be defined by the specific task subclasses that implement this method.
     *
     * @return A string representing the task in the saved format.
     */
    public abstract String toSavedFormatting();

    /**
     * If task has a deadline, will push back the deadline by specified duration.
     *
     * @param durationType A string representing the type duration in days, hours or minutes.
     * @param durationValue An int representing the value of the specified type to push back the deadline.
     *
     * @return A string representing the message to the user of the action.
     */
    public abstract String snoozeTask(String durationType, int durationValue);

    public abstract boolean isOccurringOn(ReginaDateAndTime dateAndTime);

    /**
     * Marks the task as done.
     *
     * @throws ReginaException If the task is already marked as done.
     */
    public void checkTask() throws ReginaException {
        if (isDone) {
            throw new ReginaException("Task is already marked!");
        }
        this.isDone = true;
    }

    protected int hoursToMinutes(int hours) {
        return hours * 60;
    }
    /**
     * Unmarks the task, setting it back to not done.
     *
     * @throws ReginaException If the task is already unmarked.
     */
    public void uncheckTask() throws ReginaException {
        if (!isDone) {
            throw new ReginaException("Task is already unmarked!");
        }
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status icon representing the task's completion status.
     *
     * @return "X" if the task is done, or " " if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
