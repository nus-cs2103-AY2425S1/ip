package chatbot.exception;

/**
 * Exception to be thrown when the command is not recognised
 */
public class InvalidCommandException extends InputException {
    public InvalidCommandException() {
        super("Sorry, the command entered is not in my vocabulary!");
    }
}
