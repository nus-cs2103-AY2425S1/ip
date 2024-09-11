package cow.exceptions;

/**
 * Class to handle all exceptions from the chatbot.
 */
public class CowExceptions extends Exception {
    /**
     * Creates an exception with a message.
     *
     * @param exception the message to be displayed.
     */
    public CowExceptions(String exception) {
        super(exception);
    }
}
