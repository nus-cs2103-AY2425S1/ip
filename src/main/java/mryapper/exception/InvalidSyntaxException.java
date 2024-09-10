package mryapper.exception;

/**
 * An exception thrown whenever the user input does not match the syntax of the intended command.
 */
public class InvalidSyntaxException extends Exception {

    public InvalidSyntaxException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Your syntax is incorrect!\n" + super.getMessage();
    }
}
