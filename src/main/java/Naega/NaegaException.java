package Naega;

/**
 * Represents an exception specific to the Naega application.
 * This exception is used to indicate errors related to the application's operations.
 */
public class NaegaException extends Exception {

    /**
     * Creates a NaegaException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public NaegaException(String message) {
        super(message);
    }
}