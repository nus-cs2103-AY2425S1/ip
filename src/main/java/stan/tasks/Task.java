package stan.tasks;

/**
 * Represents an abstract Task in the task list.
 * This class is intended to be extended by specific types of tasks such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * Converts the task to a storage-friendly string format.
     *
     * @return The string representation of the task for storage.
     */
    public abstract String toStorageString();

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
