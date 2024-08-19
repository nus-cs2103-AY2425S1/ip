/**
 * Represents a Todo task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Validates the description of the Todo task.
     *
     * @param command The command string used to create the task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    @Override
    public void validateDescription(String command) throws EmptyDescriptionException {
        if (command.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * Creates a Todo task from the command string.
     *
     * @param command The command string containing the task description.
     * @return The created Todo task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static Todo createTask(String command) throws EmptyDescriptionException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The Todo task's type and description.
     */
    @Override
    public String toString() {
        return "[Todo]" + super.toString();
    }
}