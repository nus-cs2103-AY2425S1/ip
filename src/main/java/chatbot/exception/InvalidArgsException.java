package chatbot.exception;

/**
 * Exception to be thrown when there is an incorrect number of deadline args
 */
public class InvalidArgsException extends InputException {
    public InvalidArgsException() {
        super("Invalid argument!");
    }
}