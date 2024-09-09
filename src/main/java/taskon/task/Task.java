package taskon.task;

import java.time.LocalDate;

import taskon.exception.TaskonException;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or undone, and to retrieve the task's status icon.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * @return String of the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed or not
     * @return true if task is done, false otherwise
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task, indicating whether it is completed or not.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    /**
     * Marks the task as done.
     *
     * @throws TaskonException if the task is already marked as done.
     */
    public void markAsDone() throws TaskonException {
        if (isDone) {
            throw new TaskonException("You have already completed this task!\n" + this + "\n");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     *
     * @throws TaskonException if the task is already marked as not done.
     */
    public void markAsUndone() throws TaskonException {
        if (!isDone) {
            throw new TaskonException("Hmm... it looks like you've already left this task unchecked.\n"
                    + this + "\n");
        }
        this.isDone = false;
    }

    /**
     * Checks if tasks occur on a specified date. Returns false by default
     * @param date The date to compare with
     * @return false by default
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string in the format "[statusIcon] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
