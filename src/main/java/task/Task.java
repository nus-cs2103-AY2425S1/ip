package task;

/**
 * The Task class represents a generic task.
 * It provides basic functionalities for marking a task as done or not done,
 * and storing the task name and its status.
 */
public class Task {

    private boolean isCompleted;
    private String taskName;

    /**
     * Constructs a new Task with the specified name.
     * The task is initially marked as not done.
     *
     * @param name The name or description of the new task.
     */
    protected Task(String name) {
        this.isCompleted = false;
        this.taskName = name.trim();
    }

    /**
     * Constructs a new Task with the specified name and completion status.
     *
     * @param name   The name or description of the task.
     * @param isDone Whether the task is marked as completed.
     */
    protected Task(String name, boolean isDone) {
        this.isCompleted = isDone;
        this.taskName = name.trim();
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isCompleted;
    }

    /**
     * Returns the name or description of the task.
     *
     * @return The task name as a String.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Converts the Task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "T," + done + "," + this.getName();
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns the string representation of the task, including its status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.taskName;
    }
}
