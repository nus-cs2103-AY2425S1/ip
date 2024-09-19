package potong.exceptions;

/**
 * Parent exception class for the chatbot.
 */
public class PotongException extends Exception {

    /**
     * Initialise the exception.
     * @param message Message for the exception.
     */
    public PotongException(String message) {
        super(message);
    }

}
