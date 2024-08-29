package stan.tasks;

/**
 * Represents a Todo task in the task list.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a storage-friendly string format.
     *
     * @return The string representation of the Todo task for storage.
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
