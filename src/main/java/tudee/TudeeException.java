package tudee;

/**
 * Represents an exception specific to Tudee chatbot
 * This exception is used for error handling within the Tudee chatbot
 */
public class TudeeException extends Exception {
    /**
     * Constructs a new TudeeException with the specified error message.
     *
     * @param message the error message
     */
    public TudeeException(String message) {
        super(message);
    }
}