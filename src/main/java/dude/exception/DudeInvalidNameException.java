package dude.exception;

/**
 * Thrown to indicate that an invalid task name is provided in the input when adding task.
 */
public class DudeInvalidNameException extends DudeException {

    /**
     * Constructs a DudeInvalidCommandException with no detail message.
     */
    public DudeInvalidNameException() {
        super("Don't put these characters in your task description: ( '|', '/' )");
    }
}
