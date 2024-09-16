package chatbot.exception;

/**
 * Exception to be thrown when event command has no /to arg
 */
public class MissingEventToException extends InputException {
    public MissingEventToException() {
        super("The Event needs a /to argument!");
    }
}
