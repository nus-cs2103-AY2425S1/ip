package chatbuddy.task;

import chatbuddy.exception.ChatBuddyException;

/**
 * Represents a task in the ChatBuddy task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Description must not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if done, " " if not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isDone : "Task is already marked as done";
        this.isDone = true;
    }

    /**
     * Unmarks the task (sets it as not done).
     */
    public void unmarkAsDone() {
        assert isDone : "Task is already not done";
        this.isDone = false;
    }

    /**
     * Updates the description of the task.
     *
     * @param newDescription The new description of the task.
     */
    public void updateDescription(String newDescription) {
        assert newDescription != null && !newDescription.trim().isEmpty() : "Description must not be null or empty";
        this.description = newDescription;
    }

    /**
     * Updates the date of the task.
     * By default, this method throws an exception as a basic task has no date.
     * Subclasses like `Deadline` or `Event` should override this method.
     *
     * @param newDate The new date of the task.
     * @throws ChatBuddyException If the task type does not have a date to update.
     */
    public void updateDate(String newDate) throws ChatBuddyException {
        throw new ChatBuddyException("This task type does not have a date to update");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the file format representation of the task.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();
}
