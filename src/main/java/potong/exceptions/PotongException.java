package potong.exceptions;

/**
 * Parent exception class for the chatbot.
 */
public class PotongException extends Exception {

    /**
     * Initialise the exception.
     * @param message
     */
    public PotongException(String message) {
        super(message);
    }

}
