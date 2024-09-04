package lutchat;

/**
 * The Todo class represents a task with no additional details beyond the description.
 * It is a subclass of Task and represents a simple task to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of the task as a string.
     * For Todo tasks, this returns "T".
     *
     * @return The type of the task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns additional details for the Todo task in a file-friendly format.
     * Since Todo tasks have no additional details, this method returns an empty string.
     *
     * @return An empty string, as Todo tasks have no additional details.
     */
    @Override
    public String additionalDescDetailsToFileFormat() {
        return "";
    }

    /**
     * Returns a string representation of the Todo task.
     * The format includes the task type indicator and the description.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
