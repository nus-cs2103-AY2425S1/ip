package duke.exceptions;

/**
 * Represents an exception that is thrown when a user enters an invalid input in the DailyTasks application.
 * This exception could be triggered when the input command is not complete, or when dates are not formatted properly.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs an InvalidInputException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public InvalidInputException(String e) {
        super(e);
    }
}
