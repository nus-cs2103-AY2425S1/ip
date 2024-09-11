package Exceptions;

/**
 * The EmptyInputException class represents a specific type of DelphiException
 * that is thrown when the task input is empty or missing.
 *
 * @author Jordan Chan
 */
public class EmptyInputException extends DelphiException {
    /**
     * Constructs a new EmptyInputException with a default error message.
     * The error message indicates that an actual task must be included.
     */
    public EmptyInputException() {
        super("please include a task");
    }
}

