package zero.task;

/**
 * The {@code Todo} class represents a task that needs to be done without any specific date or time.
 * It is a subclass of {@code Task} and inherits all of its properties and methods.
 * This class overrides methods to provide specific formatting for Todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string in the format suitable for saving to a file.
     * It specifies the task type as "T" followed by the task's status and description.
     *
     * @return A formatted string representation of the todo task.
     */
    @Override
    public String toFormatted() {
        return "T," + this.isDone() + "," + this.description + "\n";
    }

    /**
     * Returns a string representation of the {@code Todo} task, prefixed with "[T]" to indicate it is a todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
