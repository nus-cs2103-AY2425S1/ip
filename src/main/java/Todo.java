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
    public String toString() {
        return super.toString();
    }
}
