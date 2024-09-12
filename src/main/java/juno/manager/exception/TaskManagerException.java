package juno.manager.exception;
/**
 * The TaskManagerException class represents exceptions that can occur.
 * A subclass of {@code Exception} class and includes {@code ErrorType} to specify the type of error.
 */
public class TaskManagerException extends Exception {
    private final ErrorType errorType;

    /**
     * An enumeration of possible error types that can occur.
     */
    public enum ErrorType {
        EMPTY_LIST,
        INVALID_MARK_TASK_NUMBER,
        INVALID_ADD_TASK_NUMBER,
        DUPLICATE_TASK,
        TASK_OUT_OF_RANGE,
        INVALID_DATETIME_ARGUMENT,
        INVALID_FUNCTION,
        EMPTY_INPUT,
        INVALID_DELETE_TASK_NUMBER,
        NO_TASK_FOUND,
        INVALID_FIND_TASK,
        FILE_NOT_EXIST
    }

    /**
     * Constructs a TaskManagerException instance that takes in a specified detail message and error type.
     *
     * @param message the message for the exception.
     * @param errorType the type of error that occurred, based on the ErrorType enumeration.
     */
    public TaskManagerException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Returns the ErrorType of this exception.
     *
     * @return the error type of the exception.
     */
    public ErrorType getErrorType() {
        return this.errorType;
    }
}
