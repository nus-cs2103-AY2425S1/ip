package killua.util;

/**
 * Custom exception class for handling errors specific to the Killua application.
 * This class extends the standard Java {@code Exception} class to provide
 * application-specific exception handling.
 */
public class KilluaException extends Exception {

    /**
     * Constructs a KilluaException with the specified detail message.
     *
     * @param message The detail message that describes the reason for the exception.
     */
    public KilluaException(String message) {
        super(message);
    }
}
