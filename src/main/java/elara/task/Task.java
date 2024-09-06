package elara.task;

/**
 * Represents an abstract task with a description and a completion status.
 * This class serves as the base for specific types of tasks such as ToDoTask, DeadlineTask, and EventTask.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not marked as completed.
     *
     * @param desc The description of the task.
     */
    public Task(String desc) {
        description = desc;
        isDone = false;
    }

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not marked as completed.
     *
     * @param desc The description of the task.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     */
    public Task(String desc, boolean isDone) {
        description = desc;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * The string includes a symbol indicating whether the task is completed, followed by the task description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string format of the task to be saved in a file.
     * This method must be implemented by subclasses to provide the correct file format for different task types.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String toFileFormat();

}
