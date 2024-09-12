package momo.exception;

/**
 * Thrown when user gives wrongly formatted command
 */
public class InvalidCommandException extends MomoException {
    /**
     * Exception thrown when user makes an invalid command
     */
    public InvalidCommandException() {
        super("Your command is INVALID. You did not properly specify the type of task (todo/deadline/event) or "
                + "command (bye/list)");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
