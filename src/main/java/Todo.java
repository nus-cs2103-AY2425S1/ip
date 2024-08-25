/**
 * Todo class includes the name of the task.
 * Subclass of Task class.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo instance.
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

    @Override
    public String saveDetails() {
        return "T | " + super.saveDetails();
    }
}
