package duke.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }
}