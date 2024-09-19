package chatbot.exception;

/**
 * Exception to be thrown when event command has no /from arg
 */
public class MissingEventFromException extends InputException {
    public MissingEventFromException() {
        super("The Event needs a /from argument!");
    }
}
