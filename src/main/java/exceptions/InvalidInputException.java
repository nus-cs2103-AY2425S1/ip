package exceptions;

/**
 * The InvalidInputException class represents a specific type of DelphiException
 * that is thrown when the input provided does not meet the required format.
 *
 * @author jordanchan
 */
public class InvalidInputException extends DelphiException {
    /**
     * Constructs a new InvalidInputException with a default error message.
     */
    public InvalidInputException() {
        super("the input you have provided me is not formatted correctly.");
    }

    /**
     * Constructs a new InvalidInputException with a custom error message.
     * @param message any extra information specific to the method throwing the exception
     */
    public InvalidInputException(String message) {
        super("the input you have provided me is not formatted correctly." + message);
    }
}
