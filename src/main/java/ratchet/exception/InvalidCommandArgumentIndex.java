package ratchet.exception;

/**
 * Exception for command with invalid arguments for indexes.
 */
public class InvalidCommandArgumentIndex extends InvalidCommandArgumentException {
    /**
     * Constructor for an InvalidCommandArgumentIndex
     */
    public InvalidCommandArgumentIndex() {
        super("Please enter a valid task index!");
    }
}
