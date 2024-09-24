package duke;

/**
 * Exception thrown when the instruction or command returned is invalid or does not match the required syntax.
 */
public class InvalidCommandException extends Exception {
    @Override
    public String getMessage() {
        return "The instruction provided is deemed invalid.";
    }
}
