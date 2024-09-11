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
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the Task, marking it as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the Task is done.
     *
     * @return True if the Task is done, false otherwise.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the Task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the Task for file storage.
     */
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
