package duke.exceptions;

/**
 * Represents a general exception that is thrown when an unknown error occurs during program execution
 * in the DailyTasks application.
 * This exception can be used as a fallback for unexpected errors.
 */
public class GeneralException extends Exception {

    /**
     * Constructs a GeneralException with a specified error message.
     *
     * @param e The error message describing the exception.
     */
    public GeneralException(String e) {
        super(e);
    }

}
