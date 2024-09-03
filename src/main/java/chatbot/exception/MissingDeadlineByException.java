package chatbot.exception;

/**
 * Exception to be thrown when deadline command has no /by arg
 */
public class MissingDeadlineByException extends InputException {
    public MissingDeadlineByException() {
        super("The deadline needs a /by argument!");
    }
}
