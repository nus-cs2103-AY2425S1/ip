package poChat.exceptions;

public class ChatHistoryFileMissingException extends RuntimeException {
    public ChatHistoryFileMissingException() {
        super("The chat history file does not exist! If this is your first time running the chatbot, please" +
                " refer to the readme on how to initialise the chat_data.txt file for the first time");
    }
}
