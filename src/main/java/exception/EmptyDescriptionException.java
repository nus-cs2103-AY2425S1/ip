package exception;

/**
 * The EmptyDescriptionException is thrown when a task description is expected but not provided.
 * It is a specific type of ScheduloException related to tasks with missing descriptions.
 */
public class EmptyDescriptionException extends ScheduloException {


    /**
     * Constructs an EmptyDescriptionException with a specific task type.
     * The exception message indicates that the description of the task type cannot be empty.
     *
     * @param taskType The type of task that has an empty description.
     */
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}
