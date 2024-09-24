package beechat.task;

/**
 * Represents a todo task in the Beechat chatbot application.
 * A todo task is a task that needs to be done, but has no specified deadline or start and end.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask with the specified description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task for saving to a file.
     *
     * @return A formatted string representation of the task in a file-friendly format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
