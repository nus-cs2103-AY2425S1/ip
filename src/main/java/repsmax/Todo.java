package repsmax;

/**
 * Represents a task without a specific date or time.
 * <p>
 * The {@code Todo} class is a subclass of {@code Task} that denotes a task
 * without any associated date or time. It provides a specific string format
 * for representing the task.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description.
     * <p>
     * The task is initially marked as not done.
     * </p>
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * <p>
     * The format is "[T][statusIcon] description", where "[T]" indicates
     * that the task is a todo, and the status icon is either "X" (for done)
     * or " " (for not done).
     * </p>
     *
     * @return a string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
