package tars;

/**
 * Represents an exception specific to the TARS application.
 *
 * <p>The TarsException class extends the standard {@code Exception} class
 * to provide a way to signal errors that are specific to the TARS application,
 * such as issues with loading or saving tasks.
 */
public class TarsException extends Exception {

    /**
     * Constructs a new TarsException with the specified detail message.
     *
     * @param message the detail message that describes the error.
     */
    public TarsException(String message) {
        super(message);
    }
}
