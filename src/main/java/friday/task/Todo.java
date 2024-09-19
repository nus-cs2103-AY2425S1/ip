package friday.task;

/**
 * Represents a Todo task. A Todo is a type of task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description.
     * The task is not done by default.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructs a Todo with the specified description and completion status.
     *
     * @param description The description of the Todo.
     * @param isDone      True if the task is done, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return " [T] " + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (super.isTaskDone() ? "1" : "0") + " | " + super.getDescription();
    }
}
