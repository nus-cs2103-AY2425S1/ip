package task;

import exception.CommandFoundButInvalidException;
import exception.EmptyDescriptionException;

/**
 * Represents a to-do task. Inherits from the {@code Task} class and provides
 * specific functionality for managing to-do tasks.
 */
public class ToDos extends Task {
    /**
     * Constructs a {@code ToDos} object with the specified time
     *
     * @param description the description of the to-do-task specified by user
     * @throws CommandFoundButInvalidException if the description syntax is wrong
     */
    public ToDos(String description) throws CommandFoundButInvalidException {
        super(description);
        super.description = this.getValidString(description);
    }

    /**
     * Returns a string representation of the to-do task
     * @return a string representation of the to-do task
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks the description of the to-do-task
     * @param description the string input by the user
     * @return the validated description
     * @throws EmptyDescriptionException if the description is empty
     */
    private String getValidString(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        } else {
            return description;
        }
    }

    /**
     * Returns the initial description of the event in a format suitable for storage.
     * This format includes the task type, completion status, and the original description.
     *
     * @return a string representation of the event suitable for storage
     */
    @Override
    public String getInitDesc() {
        String str = super.isDone ? "1" : "0";
        return String.format("T | %s | %s", str, super.description);
    }
}
