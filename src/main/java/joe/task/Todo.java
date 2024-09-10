package joe.task;

/**
 * The {@code Todo} class represents a task without any date or time constraints.
 * It extends the {@code Task} class and provides specific behavior for serializing
 * and displaying todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code Todo} task with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Serializes the {@code Todo} task into a formatted string to be stored in a file.
     * The format is "T|isDone|description".
     *
     * @return A formatted string representing the {@code Todo} task.
     */
    @Override
    public String serialize() {
        return String.format("T|%b|%s", this.isDone, this.description);
    }

    /**
     * Returns a string representation of the {@code Todo} task for displaying to the user.
     * The format is "[T]{description}".
     *
     * @return A string representing the {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
