package exceptions;

/**
 * Custom checked exception that the Chatbot can throw and catch.
 */
public class BrockException extends Exception {
    /**
     * Stores the error message passed into the thrown exception.
     *
     * @param errorMessage Error message.
     */
    public BrockException(String errorMessage) {
        super(errorMessage);
    }
}
