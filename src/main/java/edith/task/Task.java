package edith.task;

/**
 * Represents a task with a type, description, and completion status.
 * This is an abstract class that can be extended to create specific types of tasks.
 */
public abstract class Task {
    private String typeOfTask;
    private String task;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified type and description.
     *
     * @param typeOfTask The type of the task (e.g., "[T]" for ToDo, "[D]" for Deadline).
     * @param task The description of the task.
     */
    public Task(String typeOfTask, String task) {
        assert task != null && !task.isEmpty() : "Task description should not be null or empty";
        this.typeOfTask = typeOfTask;
        this.task = task;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String taskString() {
        return this.task;
    }

    /**
     * Returns the string representation of the task to be saved to storage.
     * By default, this returns the task description.
     *
     * @return The string representation of the task for storage.
     */
    public String savedTaskString() {
        return this.task;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String typeOfTaskString() {
        return this.typeOfTask;
    }

    /**
     * Returns the status of the task as a string.
     * "[X] " indicates the task is done, "[ ] " indicates the task is not done.
     *
     * @return The status of the task.
     */
    public String statusString() {
        if (this.isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Marks the task as done.
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markTaskUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return true if the task's description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return taskString().toLowerCase().contains(keyword);
    }

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return typeOfTaskString() + statusString() + taskString();
    }
}
