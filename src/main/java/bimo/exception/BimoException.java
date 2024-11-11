package bimo.exception;

/**
 * Represents a custom Checked Exception.
 * Thrown when there are errors involving commands.
 */
public class BimoException extends Exception {
    /**
     * Instantiates the checked BimoException.
     *
     * @param message The corresponding error message.
     */
    public BimoException(String message) {
        super(message);
    }
}
