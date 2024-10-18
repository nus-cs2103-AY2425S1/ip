package beeboo.exception;

/**
 * Represents an exception that is thrown when an invalid command is provided.
 * This exception is used to indicate that the command inputted does not exist.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command! Please enter a valid command.");
    }
}
