package ollie.task;

import ollie.exception.EmptyDescriptionException;
import ollie.TaskType;

/**
 * Represents a ollie.task.Todo task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a ollie.task.Todo task with the specified description.
     *
     * @param description The description of the ollie.task.Todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public void validateDescription(String command) throws EmptyDescriptionException {
        if (command.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
    }

    public static Todo createTask(String command) throws EmptyDescriptionException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(description);
    }

    @Override
    public String saveAsString() {
        return super.saveAsString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
