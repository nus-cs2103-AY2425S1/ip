package lbot.exception;

/**
 * This exception is thrown when the chatbot
 * encounters issues parsing command arguments.
 * <p>
 * For example, the provided datetime format is incorrect.
 */
public class ParseCommandException extends LBotException {
    public ParseCommandException(String message) {
        super(message);
    }
}
