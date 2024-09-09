package handsome.exception;

/**
 * The InvalidSyntaxException class is a custom exception that extends
 * HandsomeException and is thrown when the user inputs an invalid syntax
 * command in the Handsome chatBot application.
 * This exception provides a specific error message guiding the user to check
 * the correct command format.
 */
public class InvalidSyntaxException extends HandsomeException {
    public InvalidSyntaxException() {
        super("Invalid Syntax! Type prompt to check for valid command");
    }
}
