package seedu.avo.ui;

/**
 * Represents the response to the user
 */
public class Response {
    private final String message;
    private final boolean isExit;

    /**
     * @param message String response from Avo
     * @param isExit Status to check whether to close the application
     */
    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }
    public String getMessage() {
        return message;
    }
    public boolean getExitStatus() {
        return isExit;
    }
}
