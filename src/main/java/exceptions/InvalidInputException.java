package exceptions;

/**
 * The InvalidInputException class is a custom exception that is thrown when the user
 * inputs an unrecognized or invalid command.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified error message.
     *
     * @param message The detailed error message to describe the invalid input.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
