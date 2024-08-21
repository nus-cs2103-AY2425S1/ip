package exceptions;

/**
 * Custom exception class for Duke application.
 * This class extends the standard Exception class to provide
 * specific error handling for the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param e the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public DukeException(String e) {
        super(e);
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this DukeException instance
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
