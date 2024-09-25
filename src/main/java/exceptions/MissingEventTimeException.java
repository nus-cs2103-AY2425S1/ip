package exceptions;

/**
 * Thrown to indicate that an event time is missing or incorrectly specified in a command.
 * <p>
 * This exception is used when an event-related operation encounters an invalid or missing event time value.
 * </p>
 */
public class MissingEventTimeException extends Exception {
    /**
     * Constructs a {@code MissingEventTimeException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public MissingEventTimeException(String message) {
        super(message);
    }
}