package bruno.exceptions;

/**
 * Exception should be thrown when a command is run on an invalid task index.
 */
public class InvalidTaskIndexException extends BrunoException {
    public InvalidTaskIndexException() {
        super("The task number is invalid, try the command 'list' first");
    }
}
