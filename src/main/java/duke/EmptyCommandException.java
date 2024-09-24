package duke;

/**
 * Exception thrown when a command without the necessary description is entered by the user.
 */
public class EmptyCommandException extends Exception {
    @Override
    public String getMessage() {
        return "An empty command has been received.";
    }
}
