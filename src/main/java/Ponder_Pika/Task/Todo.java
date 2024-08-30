package Ponder_Pika.Task;

/**
 * This class represents a specific type of task that just has a description.
 * It extends the {@code Task} class and overrides the following methods from the {@code Task} class:
 * saveFullDetails() and toString()
 */
 public class Todo extends Task {

    /**
     * Constructs a To-do task with specified description
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representing the full details of the task.
     * The format of the returned string is:
     * <pre>
     *   T | [completion status] | [description]
     * </pre>
     *
     * @return a string representing the full details of the task
     */
    @Override
    public String saveFullDetails() {
        return String.format("T | %b | %s", isDone(), getDescription());
    }

    /**
     * Returns a string representation of the task suitable for display.
     * The format of the returned string is:
     * <pre>
     *   [T][description]
     * </pre>
     *
     * @return a formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

