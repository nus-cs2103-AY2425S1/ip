package delta.task;

/**
 * Subclass of Task class.
 * Includes the name of the task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo instance.
     *
     * @param description Name of todo task.
     */
    public Todo(String description) {
        super(description, TaskType.Todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats Todo for saving.
     *
     * @return Formatted string containing details of Todo.
     */
    @Override
    public String saveDetails() {
        return "T | " + super.saveDetails();
    }
}
