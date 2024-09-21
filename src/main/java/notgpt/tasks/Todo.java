package notgpt.tasks;

/**
 * Represents a simple task with no specific time constraints.
 * <p>
 * A {@code Todo} task extends {@code Task} and is identified by its type "[T]" in its string representation.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} object with the specified task description.
     * <p>
     * This constructor initializes the task with the given description and
     * marks it as not completed.
     * </p>
     *
     * @param s the description of the todo task
     */
    public Todo(String s) {
        super(s);
    }

    /**
     * Returns a string representation of the todo task, including its type.
     * <p>
     * The string format is "[T][status] task description", where "[status]" is "[X]" if completed,
     * or "[ ]" if not.
     * </p>
     *
     * @return a string representing the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
