package soju.tasks;

/**
 * The {@code Todo} class represents a task of type "Todo".
 * It extends the {@code Task} class and provides specific functionality
 * for "Todo" tasks, including formatting for display and file storage.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the "Todo" task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the "Todo" task.
     * The string includes the task type indicator "[T]" followed by the status and description.
     *
     * @return A string representation of the "Todo" task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of the "Todo" task formatted for saving to a file.
     * The format is "T | <status> | <description>", where <status> is 1 if the task is done
     * and 0 if the task is not done.
     *
     * @return A string representation of the "Todo" task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
