package bob;

/**
 * Represents an exception specific to the Bob chatbot.
 * This exception is thrown when the chatbot encounters an error during execution.
 */
public class ChatBotException extends Exception {

    /**
     * Constructs a new ChatBotException with the specified detail message.
     *
     * @param message The detail message for the exception
     */
    public ChatBotException(String message) {
        super(message);
    }
}
