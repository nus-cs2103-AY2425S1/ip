package bimo.exception;

/**
 * Represents an Exception thrown when required dates are not provided.
 */
public class MissingDateException extends BimoException {

    /**
     * Instantiates a MissingDateException.
     *
     * @param message Error message for the missing date.
     */
    public MissingDateException(String message) {
        super(message);
    }
}
