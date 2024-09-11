package duke.exceptions;

/**
 * Represents an exception that is thrown when a user enters an invalid event in the DailyTasks application.
 * This exception typically occurs when the `from` or `to` time for the event is incorrectly formatted or invalid.
 */
public class InvalidEventException extends Exception {

    /**
     * Constructs an InvalidEventException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public InvalidEventException(String e) {
        super(e);
    }
}
