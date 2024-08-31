package tasks;

/**
 * Represents a basic to-do task.
 *
 * <p>The {@code ToDo} class extends the {@link Task} class and represents a simple task
 * that does not have any date or time constraints. It provides functionality to format
 * the task details for saving and display purposes.</p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the specified name.
     *
     * @param name The name of the to-do task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Prepares the to-do task information for saving to a file.
     *
     * @return A {@link String} representing the serialized task information in the format:
     *         "T|status|taskName".
     */
    @Override
    public String saveTask() {

        return String.format("T|%s|%s", super.getStatus(), super.getTask());
    }

    /**
     * Returns a string representation of the to-do task, including its completion status.
     *
     * @return A formatted string representing the task's status and name.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
