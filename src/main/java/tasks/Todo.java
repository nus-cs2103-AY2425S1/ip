package tasks;

/**
 * The {@code Todo} class represents a simple task without any specific date or time associated with it.
 * It extends the {@code Task} class and is used for tasks that need to be done but
 * do not have a deadline or event time.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified name.
     *
     * @param name The name or description of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Todo task, including its completion status and name.
     *
     * @return A string representation of the task in the format "[T] name", where "T" denotes a Todo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
