package seedu.avo;

/**
 * Represents the response to the user
 */
public class Response {
    private final String message;
    public Response(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
