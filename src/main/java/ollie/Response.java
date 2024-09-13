package ollie;

public class Response {
    private String message;

    private boolean isExit;

    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }


    /**
     * Return whether this response represents the end of the conversation.
     *
     * @return boolean True if this response is for the end of conversation, false if otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Return response's message
     */
    public String getMessage() {
        return message;
    }
}
