package chatbot.exception;

/**
 * Exception to be thrown when there are empty args
 */
public class EmptyArgsException extends InputException {
    public EmptyArgsException() {
        super("The arguments cannot be empty!");
    }
}
