package nayana.task;
/**
 * Represents a task with a description and completion status.
 * This class provides methods to retrieve the task description,
 * mark the task as done or not done, and represent the task as a string.
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the date-related information of the task.
     *
     * @return A string representing the task's dates.
     */
    public String getDates() {
        return "";
    }

    /**
     * Returns the status of the task, indicating whether it is done or not.
     *
     * @return A string representing the task's status.
     */
    public String getStatus() {
        return done ? "| 1 |" : "| 0 |";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task's status and description.
     */
    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns the type of task.
     * Subclasses should override this method to provide specific task types.
     *
     * @return A string representing the type of the task.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return A boolean indicating the completion status of the task.
     *         Returns true if the task is done, false otherwise.
     */
    public boolean getDone() {
        return done;
    }

}
