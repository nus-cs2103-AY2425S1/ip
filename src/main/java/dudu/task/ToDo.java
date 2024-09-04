package dudu.task;

/**
 * Represents a to-do task
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified description and due date.
     * By default, the task is uncompleted.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the task into a string for storage, including the task type ("D" for deadline),
     * its completion status, description, and due date.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        return String.format("T | %s", super.formatString());
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked) and its description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
