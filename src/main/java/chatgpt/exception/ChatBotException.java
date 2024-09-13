package chatgpt.exception;

/**
 * The ChatBotException is a custom exception that is thrown when an error
 * related to the chatbot occurs.
 */
public class ChatBotException extends Exception {
    /**
     * Constructs a ChatBotException with the given error message.
     */
    public ChatBotException(String errorMessage) {
        super(errorMessage);
    }
}
