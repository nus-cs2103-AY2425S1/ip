package stan.exceptions;

/**
 * Represents an exception thrown when a required argument is missing.
 */
public class StanMissingArgumentException extends StanException {
    /**
     * Constructs a StanMissingArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public StanMissingArgumentException(String message) {
        super(message);
    }
}
