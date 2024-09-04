package seedu.avo;

public class Response {
    private String message;
    private boolean isExit;
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
