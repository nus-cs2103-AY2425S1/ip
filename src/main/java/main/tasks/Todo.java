package main.tasks;

/**
 * Todo represents a todo task and is a subclass of the Task class.
 */
public class Todo extends Task {
    /**
     * A constructor for Todo.
     * @param description String representing the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T .. " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
