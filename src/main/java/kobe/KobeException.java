package kobe;

/**
 * Represents an exception specific to the Kobe chatbot application.
 * This exception is used to handle application-specific errors gracefully.
 */
public class KobeException extends Exception {

    /**
     * Constructs a new KobeException with the specified detail message.
     *
     * @param message The detail message for the exception, providing more information about the error.
     */
    public KobeException(String message) {
        super(message);
    }
}
