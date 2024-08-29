package bobby;

import bobby.exception.EmptyDescriptionException;

/**
 * Todo is a type of task.
 * It represents a task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo task from a string input given by the user.
     *
     * @param input Command given by the user.
     * @return The Todo task created from the input.
     * @throws EmptyDescriptionException If the user did not provide any description.
     */
    public static Todo createTodo(String input) throws EmptyDescriptionException {
        String todoDescription = input.substring(4).trim();
        if (todoDescription.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(todoDescription);
    }
    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String taskInFile() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }
}
