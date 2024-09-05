package snowy.tasklist;

/**
 * Represents a task with a description and completion status.
 *
 * The Task class provides the basic structure for a task, including its description
 * and whether it has been completed. It includes methods to toggle the completion status,
 * retrieve the status icon, and return a string representation of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Toggles the task's completion status.
     * If the task is currently marked as done, it will be marked as not done, and vice versa.
     */
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return a string in the format "[statusIcon] description"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
