package tars;

/**
 * Represents a simple task without any specific date or time associated with it.
 *
 * <p>The Todo class extends the Task class, providing a basic task type that
 * only has a name and a completion status. Unlike other task types, such as
 * Deadline or Event, a Todo task does not have a date or time associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified name and completion status.
     *
     * @param name the name or description of the todo task.
     * @param done the completion status of the todo task; {@code true} if the task is done,
     *             {@code false} if the task is not done.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * <p>This method overrides the {@code toString} method from the Task class to
     * include a "[T]" prefix indicating that the task is a todo task.
     *
     * @return a string representation of the todo task, including the "[T]" prefix.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
