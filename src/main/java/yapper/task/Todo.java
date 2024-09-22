package yapper.task;

/**
 * Represents a todo task. A todo task is a task with a description
 * but no specific date or deadline.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task for saving to a file.
     *
     * @return A string in the format "T | status | description".
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * Returns the string representation of the todo task for display.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
