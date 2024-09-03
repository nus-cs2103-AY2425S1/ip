package chatbot.exception;

/**
 * Exception to be thrown when user inputs an invalid index
 */
public class InvalidIndexException extends InputException {
    public InvalidIndexException() {
        super("The index entered is invalid!");
    }
}
