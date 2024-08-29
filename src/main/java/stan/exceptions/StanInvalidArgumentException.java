package stan.exceptions;

/**
 * Represents an exception thrown when an invalid argument is provided.
 */
public class StanInvalidArgumentException extends StanException {
    /**
     * Constructs a StanInvalidArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public StanInvalidArgumentException(String message) {
        super(message);
    }
}
