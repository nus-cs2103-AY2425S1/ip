package soju;

/**
 * The {@code SojuException} class represents a custom exception for the Soju application.
 * It is used to handle specific error cases with a custom message.
 */
public class SojuException extends Exception {

    /**
     * Constructs a new {@code SojuException} with the specified detail message.
     * The message is prefixed with "OOPS!!! " to provide a custom error indication.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public SojuException(String message) {
        super("OOPS!!! " + message);
    }
}
