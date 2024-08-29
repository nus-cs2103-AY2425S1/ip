package duke;

/**
 * Exception class for the app.
 * This exception is thrown when an error occurs during task processing.
 */
public class MeowException extends Exception {
    public MeowException(String message) {
        super(message);
    }
}
