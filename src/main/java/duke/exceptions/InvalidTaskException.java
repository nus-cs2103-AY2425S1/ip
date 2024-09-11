package duke.exceptions;

/**
 * Represents an exception that is thrown when a user enters an invalid task in the DailyTasks application.
 * This exception could be triggered when there is an error thrown when processing todos/ deadlines/ events.
 */
public class InvalidTaskException extends Exception {

    /**
     * Constructs an InvalidTaskException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public InvalidTaskException(String e) {
        super(e);
    }
}
