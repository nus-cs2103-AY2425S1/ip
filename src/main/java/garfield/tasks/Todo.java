package garfield.tasks;

/**
 * The Todo class represents a task of type "todo" in the Garfield chatbot application.
 * It extends the Task class to provide a specific representation for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the specified description.
     *
     * @param taskDescription The description of the todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a string representation of the todo task suitable for display to the user.
     * The representation includes a "[T]" prefix to indicate that it is a todo task,
     * along with the completion status and task description.
     *
     * @return A string representation of the todo task, with "[T]" indicating the task type.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task suitable for saving to storage.
     * The representation includes a "T | " prefix to indicate that it is a todo task,
     * followed by the completion status and task description.
     *
     * @return A string representation of the todo task, with "T | " indicating the task type.
     */
    @Override
    public String toSaveRepresentation() {
        return "T | " + super.toSaveRepresentation();
    }
}
