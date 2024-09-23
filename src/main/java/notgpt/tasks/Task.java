package notgpt.tasks;
/**
 * The {@code Task} class represents a basic task with a description and a completion status.
 * <p>
 * Tasks can be marked as completed or not completed, and their status is reflected in their string representation.
 * </p>
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructs a {@code Task} object with the specified task description.
     * <p>
     * The task is initially marked as not completed.
     * </p>
     *
     * @param s the description of the task
     */
    public Task(String s) {
        task = s;
        isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void uncomplete() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     * <p>
     * The string format is "[X] task description" if the task is completed, or "[ ] task description" if not.
     * </p>
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + task;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Checks if the task has a valid, non-empty description.
     *
     * @return {@code true} if the task description is not empty, {@code false} otherwise
     */
    public boolean isReal() {
        return !task.isEmpty();
    }
}
