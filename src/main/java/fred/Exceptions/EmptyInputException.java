package fred.Exceptions;

/**
 * The EmptyInputException class represents an exception that is thrown when
 * the user provides an empty input.
 */
public class EmptyInputException extends FredException {

    /**
     * Constructs an EmptyInputException with a default error message indicating
     * that the user has provided no input.
     */
    public EmptyInputException() {
        super("OOPS!!! I think you forgot to type something.");
    }
}
