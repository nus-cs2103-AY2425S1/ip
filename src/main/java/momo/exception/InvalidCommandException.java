package momo.exception;

/**
 * Thrown when user gives a wrongly formatted command input.
 */
public class InvalidCommandException extends MomoException {
    /**
     * Exception thrown when user makes an invalid command.
     */
    public InvalidCommandException() {
        super("Your command is INVALID. You did not properly specify the type of task you wanted to add "
                + "(todo/deadline/event/archive) or command (bye/list/mark/unmark/find)");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
