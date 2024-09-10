package bob.exceptions;

/**
 * InvalidInputException is thrown when the user inputs an unrecognized command
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for InvalidInputException
     */
    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
