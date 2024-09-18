package bro.task;

/**
 * Represents a task tracked by Bro. A <code>Task</code> object encapsulates
 * the content of the task and its completion status.
 */
public class Task {
    private final String content;
    private boolean isCompleted;

    /**
     * Constructs a new Task with the specified content.
     * The task is initially marked as not completed.
     *
     * @param content The content of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    /**
     * Constructs a new Task with the specified content and completion status.
     *
     * @param content     The content of the task.
     * @param isCompleted The initial completion status of the task.
     */
    public Task(String content, boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markTask() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmarkTask() {
        this.isCompleted = false;
    }

    /**
     * Returns the string representation of the task,
     * indicating its content and completion status.
     *
     * @return A string representation of the task, showing
     *         its completion status and content.
     */
    @Override
    public String toString() {
        return (this.isCompleted) ? "[X] " + this.content : "[] " + this.content;
    }
}
