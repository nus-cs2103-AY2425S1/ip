package moimoi.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task of the specified description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(TaskEnum.T, description);
    }

}
