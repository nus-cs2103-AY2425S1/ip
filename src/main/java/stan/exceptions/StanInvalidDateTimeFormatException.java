package stan.exceptions;

/**
 * Represents an exception thrown when an invalid date/time format is provided.
 */
public class StanInvalidDateTimeFormatException extends StanInvalidArgumentException {
    /**
     * Constructs a StanInvalidDateTimeFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public StanInvalidDateTimeFormatException(String message) {
        super(message);
    }
}
