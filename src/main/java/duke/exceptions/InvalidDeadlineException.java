package duke.exceptions;

/**
 * Represents an exception that is thrown when a user enters an invalid deadline format in the DailyTasks application.
 * This exception typically occurs when the deadline input is incorrectly formatted, such as when there are
 * too many spaces or the format is otherwise not as expected.
 * <p>
 * Examples of valid deadline formats:
 * <ul>
 *     <li>2/12/2019 1800</li>
 *     <li>2/12/2019</li>
 * </ul>
 * </p>
 */
public class InvalidDeadlineException extends Exception {

    /**
     * Constructs an InvalidDeadlineException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public InvalidDeadlineException(String e) {
        super(e);
    }
}
