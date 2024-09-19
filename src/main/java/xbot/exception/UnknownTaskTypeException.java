package xbot.exception;

/**
 * Exception thrown when an unknown task type is encountered.
 */
public class UnknownTaskTypeException extends Exception {

    /**
     * The task type that caused the exception.
     */
    private String taskType;

    /**
     * Constructs a new UnknownTaskTypeException with the specified message and task type.
     *
     * @param message The error message.
     * @param taskType The task type that caused the exception.
     */
    public UnknownTaskTypeException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return A detailed message about the exception.
     */
    @Override
    public String getMessage() {
        return "I do not know this taskType...: " + taskType;
    }
}
