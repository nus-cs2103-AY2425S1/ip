package rapgod.tasks;

/**
 * Represents a "To-Do" task that needs to be completed.
 * This subclass of {@link Task} is used to indicate tasks that do not have a specific deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     * The task is initialized with a completion status of {@code false}.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string is formatted to include the task type identifier "[T]",
     * the completion status, and the description of the task.
     * The completion status is indicated by "[X]" if the task is completed, or "[ ]" if it is not.
     *
     * @return A string representation of the ToDo task in the format "[T] [status] description".
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[T] [%s] %s", mark, super.description);
    }
}
