package lbot.task;

/**
 * Represents a {@link Todo} task.
 */
public class Todo extends Task {
    private final String taskType = "[T]";

    /**
     * Constructs a {@link Todo} task.
     *
     * @param description of the todo.
     */
    public Todo(String description) {
        super(description);
        this.type = this.taskType;
    }

    /**
     * Constructs a {@link Todo} task.
     * Allows status of todo to be declared.
     *
     * @param description of the todo.
     * @param isComplete  status of the todo.
     */
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description;
    }
}
