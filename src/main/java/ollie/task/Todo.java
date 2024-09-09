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
    }

    /**
     * Validates the description of the Todo task.
     *
     * @param command The command entered by the user.
     * @throws EmptyDescriptionException If the description is empty.
     */
    @Override
    public void validateDescription(String command) throws EmptyDescriptionException {
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
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(description);
    }
}
