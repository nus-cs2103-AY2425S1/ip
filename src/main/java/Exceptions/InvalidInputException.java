package Exceptions;

/**
 * The InvalidInputException class represents a specific type of DelphiException
 * that is thrown when the input provided does not meet the required format.
 *
 * @author jordanchan
 */
public class InvalidInputException extends DelphiException {
    /**
     * Constructs a new InvalidInputException with a default error message.
     * The error message indicates that the input must start with 'todo', 'deadline', or 'event'.
     */
    public InvalidInputException() {
        super("the input you have provided me is not formatted correctly. "
                + "Please give me an input starting with todo, deadline or event");
    }
}
