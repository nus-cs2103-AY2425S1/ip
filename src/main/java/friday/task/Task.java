package friday.task;

/**
 * Represents an abstract Task. A Task can be marked as done or not done and has a description.
 * This class serves as a base class for specific types of tasks like Todo, Deadline, and Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the Task.
     * @param isDone      True if the task is done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task, marking it as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the Task is done.
     *
     * @return True if the Task is done, false otherwise.
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the Task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the Task for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the Task for displaying to the user.
     *
     * @return A string representing the Task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}