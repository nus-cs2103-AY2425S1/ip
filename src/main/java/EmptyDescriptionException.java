/**
 * Exception thrown when a task description is empty.
 */
public class EmptyDescriptionException extends OllieException {
    /**
     * Constructs an EmptyDescriptionException with a predefined message based on the task type.
     *
     * @param taskType the type of task that caused the exception
     */
    public EmptyDescriptionException(String taskType) {
        super("Please add a name for a " + taskType + " task!");
    }
}