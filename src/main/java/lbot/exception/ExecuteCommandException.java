package lbot.exception;

/**
 * This exception is thrown when the chatbot encounters issues executing a command.
 * <p>
 * For example, if an incorrect command keyword is provided.
 */
public class ExecuteCommandException extends LBotException {
    public ExecuteCommandException(String message) {
        super(message);
    }
}
