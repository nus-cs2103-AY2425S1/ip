package bob;

/**
 * Represents a Task in the Bob chatbot. This class is abstract and provides common functionality
 * for different types of tasks, such as ToDos, Deadlines, and Events.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private TaskType type;

    /**
     * Constructs a new Task with the given description and task type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a new Task with the given description, done status, and task type.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done or not.
     * @param type The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Retrieves the status icon of the task.
     * "X" represents a completed task, and " " represents an incomplete task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and returns a confirmation message.
     *
     * @return A string confirming that the task has been marked as done.
     */
    public String mark() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    /**
     * Marks the task as not done and returns a confirmation message.
     *
     * @return A string confirming that the task has been marked as not done.
     */
    public String unmark() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    /**
     * Deletes the task and returns a confirmation message.
     *
     * @return A string confirming that the task has been deleted.
     */
    public String delete() {
        return "Noted. I've removed this task:\n  " + this;
    }

    /**
     * Returns a string representation of the task, which includes its type, status, and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.name().charAt(0), this.getStatusIcon(), this.description);
    }

    /**
     * Formats the task into a string suitable for saving to a file.
     * The format includes the task type, done status, and description.
     *
     * @return A formatted string for saving the task.
     */
    public String formatToSave() {
        String done = this.isDone ? "1" : "0";
        return String.format("%s | %s | %s", this.type.name().charAt(0), done, this.description);
    }
}
