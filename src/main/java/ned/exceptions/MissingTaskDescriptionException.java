package ned.exceptions;


/**
 * Exception thrown when a task description is missing.
 *
 * <p>This exception indicates that an operation has failed because a task
 * description was expected but not provided, possibly due to user input error or incomplete
 * task creation. It extends {@link NedException} to provide a specific exception type
 * for missing task description-related errors within the NED application.
 *
 * @see NedException
 */
public class MissingTaskDescriptionException extends NedException {

    /**
     * Constructs a new {@code MissingTaskDescriptionException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}

