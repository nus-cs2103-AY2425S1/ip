package astor.exception;

/**
 * Represents an exception where the user did not enter any input.
 */
public class EmptyInputException extends AstorException {
    public EmptyInputException() {
        super("Please type something!");
    }
}
