package ned.exceptions;

/**
 * Exception thrown when a task "from" date is missing.
 *
 * <p>This exception indicates that an operation has failed because a "from" date for the task
 * was expected but not provided, possibly due to user input error or incomplete task setup.
 * It extends {@link NedException} to provide a specific exception type for missing task "from" date-related errors
 * within the NED application.
 *
 * @see NedException
 */
public class MissingTaskFromDateException extends NedException {

    /**
     * Constructs a new {@code MissingTaskFromDateException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public MissingTaskFromDateException(String errorMessage) {
        super(errorMessage);
    }
}
