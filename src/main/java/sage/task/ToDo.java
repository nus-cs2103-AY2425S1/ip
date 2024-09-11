package sage.task;

import sage.exception.SageException;

/**
 * Represents a todo task in the task management applicaton
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description
     *
     * @param description The description of the todo task
     * @throws SageException If the description is empty
     */
    public ToDo(String description) throws SageException {
        super(description);
        if (description.isEmpty()) {
            throw new SageException("Oh No! I don't know what task to do :(");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
