/**
 * Abstract class representing a generic Task.
 * This class provides basic functionalities for a task,
 * such as marking the task as done, unmarking it, and displaying its status.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task: true if done, false otherwise. */
    protected Boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially not marked as done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Abstract method for creating a specific type of task.
     * This method must be implemented by any subclass.
     *
     * @param input the input string containing the task details.
     * @return the created Task object.
     * @throws InputException if the input is invalid or incomplete.
     */
    public abstract Task createTask(String input) throws InputException;

    /**
     * Returns the status icon of the task.
     * 'X' indicates the task is done, while a space indicates it is not done.
     *
     * @return a string containing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task by setting isDone to false.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
