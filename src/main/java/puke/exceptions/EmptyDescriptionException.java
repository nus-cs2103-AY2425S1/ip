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
}
