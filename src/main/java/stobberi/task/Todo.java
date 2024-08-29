package stobberi.task;

/**
 * Represents a Todo task, which is a specific type of {@link Task}.
 * It can be initialized with a description and optionally a status indicating whether it is done.
 * The {@code Todo} class inherits from the {@code Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description.
     * The task is initially marked not done.
     *
     * @param description A description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} with the specified description and done status.
     * If the {@code done} parameter is "1", the task is marked as done.
     *
     * @param description A description of the Todo task.
     * @param done A string indicating the done status. If "1", the task is marked as done.
     */
    public Todo(String description, String done) {
        super(description);
        if (done.equals("1")) {
            super.setDone();
        }
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     * The string format includes a "[T]" prefix followed by the task's description and status.
     *
     * @return A string representation of the {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
