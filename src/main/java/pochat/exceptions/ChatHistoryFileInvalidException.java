package pochat.exceptions;

/**
 * Throws when the bot attempts to read the stored chat history
 *     but the file is not file at the directory as specified
 */
public class ChatHistoryFileMissingException extends RuntimeException {
    /**
     * Outputs the error message to instructor the user
     *     on how to set up the chat history file
     */
    public ChatHistoryFileMissingException() {
        super("The chat history file does not exist! If this is your first time running the chatbot, please"
                + " refer to the readme on how to initialise the chat_data.txt file for the first time");
    }
}
