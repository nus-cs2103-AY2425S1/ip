package exception;

/**
 * The ScheduloException class is a custom exception used in the Schedulo application.
 * It serves as the base class for all custom exceptions related to the application.
 */
public class ScheduloException extends Exception {

    /**
     * Constructs a ScheduloException with a specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public ScheduloException(String message) {
        super(message);
    }

}
