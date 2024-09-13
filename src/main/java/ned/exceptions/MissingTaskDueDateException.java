package ned.exceptions;

/**
 * Exception thrown when a task due date is missing.
 *
 * <p>This exception indicates that an operation has failed because a task
 * due date was expected but not provided, possibly due to user input error or incomplete
 * task creation. It extends {@link NedException} to provide a specific exception type
 * for missing task due date-related errors within the NED application.
 *
 * @see NedException
 */
public class MissingTaskDueDateException extends NedException {

    /**
     * Constructs a new {@code MissingTaskDueDateException} with the specified detail message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public MissingTaskDueDateException(String errorMessage) {
        super(errorMessage);
    }
}

