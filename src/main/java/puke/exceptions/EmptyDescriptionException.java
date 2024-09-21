package puke.exceptions;

/**
 * Exception thrown when a task description is empty.
 */
public class EmptyDescriptionException extends PukeException {

    /**
     * Constructs an EmptyDescriptionException with a specified task type.
     *
     * @param taskType the type of the task for which the description is empty
     */
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }

    /**
     * Constructs an EmptyDescriptionException without a specific task type,
     * useful for contexts where task type is not directly relevant.
     */
    public EmptyDescriptionException() {
        super("OOPS!!! The task description cannot be empty.");
    }
}
