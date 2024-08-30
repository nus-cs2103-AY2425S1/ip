package Ponder_Pika.Task;

/**
 * This class represents an abstract task with a description and status (done or not done).
 * It provides methods to mark the task as done or undone, check its status, and get its description.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done, hence isDone being false.
     *
     * @param description the description of the task
     */
    public Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done, hence isDone is assigned to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task (not done), hence isDone is assigned to false.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     * The status is represented as "X" if the task is done, otherwise as a white space.
     *
     * @return a string representing the task status
     */
    public String taskStatus() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return (this.isDone);
    }

    /**
     * Returns the description of the task.
     * Acts as a getter.
     *
     * @return a string containing the task description
     */
    public String getDescription() {
        return (this.description);
    }

    /**
     * Abstract method that must be implemented by subclasses to provide a
     * string representation of the task's full details for saving and displaying purposes.
     *
     * @return a string containing the full details of the task
     */
    public abstract String saveFullDetails();

    /**
     * Returns a string representation of the task.
     * The format is "[status] description", where status is either "X" (done) or a space (not done).
     *
     * @return a formatted string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.taskStatus(), this.description);
    }
}
