package ollie;

/**
 * Represents a response which contains a message to be displayed to the user.
 * It also tells us if this will be the final response from the bot (i.e. to exit).
 */
public class Response {
    private String message;

    private boolean isExit;

    /**
     * Constructs a response object which represents our chatbot's reponse.
     */
    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Returns whether this response represents the end of the conversation.
     *
     * @return boolean True if this response is for the end of conversation, false if otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns response's message
     */
    public String getMessage() {
        return message;
    }
}
