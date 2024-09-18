package pochat.exceptions;

/**
 * Throws when the bot attempts to read the stored chat history
 *     but the file is not file at the directory as specified
 */
public class ChatHistoryFileInvalidException extends RuntimeException {
    /**
     * Outputs the error message to instructor the user
     *     on how to set up the chat history file
     */
    public ChatHistoryFileInvalidException() {
        super("The chat history file cannot be set up! For more information, please"
            + " contact the maintainers of the chatbot on github");
    }
}
