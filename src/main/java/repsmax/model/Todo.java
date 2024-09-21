package repsmax.model;

/**
 * Represents a task without a specific date or time.
 * <p>
 * The {@code Todo} class is a subclass of {@code Task} that denotes a task
 * without any associated date or time. It provides a specific string format
 * for representing the task and extends the basic task functionality to
 * prioritize tasks.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description and priority.
     * <p>
     * The task is initially marked as not done.
     * </p>
     *
     * @param description the description of the todo task.
     * @param priority    the priority level of the task (1 = high, 2 = medium, 3 = low).
     */
    public Todo(String description, int priority) {
        super(description, priority);
    }

    /**
     * Returns a string representation of the todo task.
     * <p>
     * The format is "[T][statusIcon] [priority] description", where "[T]" indicates
     * that the task is a todo, and the status icon is either "X" (for done)
     * or " " (for not done), followed by the task's priority and description.
     * </p>
     *
     * @return a string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
