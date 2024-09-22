package panorama.task;

/**
 * Represents a todo task.
 * A todo task is a simple task with a name and no specific time or deadline.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified name.
     * The task type is automatically set to 'T'.
     *
     * @param name The name or description of the todo task.
     */
    public Todo(String name) {
        super(name, 'T');
    }
}
