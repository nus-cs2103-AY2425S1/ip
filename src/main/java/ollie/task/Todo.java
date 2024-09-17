package ollie.task;

import ollie.exception.EmptyDescriptionException;

/**
 * Todo is a type of Task.
 * It represents a task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
        assert description != null : "Oops! Description cannot be null.";
    }

    /**
     * Validates the description of the Todo task.
     *
     * @param command The command entered by the user.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static void validateDescription(String command) throws EmptyDescriptionException {
        if (command.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * Creates a Todo task from the specified command.
     *
     * @param command The command entered by the user.
     * @return The Todo task created from the command.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static Todo createTask(String command) throws EmptyDescriptionException {
        String description = command.substring(4).trim();
        validateDescription(description);
        return new Todo(description);
    }
}
