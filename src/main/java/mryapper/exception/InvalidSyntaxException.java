package mryapper.exception;

/**
 * An exception thrown whenever the user input does not match the syntax of the intended command.
 */
public class InvalidSyntaxException extends Exception {
    private final String syntax;

    public InvalidSyntaxException(String errorMessage, String syntax) {
        super(errorMessage);
        this.syntax = syntax;
    }

    public InvalidSyntaxException(String errorMessage) {
        super(errorMessage);
        this.syntax = "";
    }

    @Override
    public String getMessage() {
        return "Your syntax is incorrect!\n" + super.getMessage() + "\n" + syntax;
    }
}
