package chatbot.exception;

/**
 * Exception to be thrown when there is an incorrect number of deadline args
 */
public class DeadlineArgsException extends InputException {
    public DeadlineArgsException() {
        super("There are an incorrect number of deadline arguments!");
    }
}
