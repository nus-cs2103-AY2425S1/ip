package duke.exceptions;

/**
 * Represents an exception that is thrown when the user enters a message that is not recognized as a valid task in
 * the DailyTasks application.
 * This exception is used to signal that the input does not correspond to any known command or task.
 */
public class UnknownMessageException extends Exception {

    /**
     * Constructs an UnknownMessageException with no error message.
     * This is used when the exception needs to be thrown without a specific error message.
     */
    public UnknownMessageException() {}

    /**
     * Constructs an UnknownMessageException with a specified error message.
     * This constructor is used when additional details about the exception are needed.
     *
     * @param e The error message describing the exception.
     */
    public UnknownMessageException(String e) {
        super(e);
    }
}
