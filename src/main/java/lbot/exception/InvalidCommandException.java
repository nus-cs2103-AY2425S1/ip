package lbot.exception;

/**
 * This exception is thrown when the user provides an incorrect number of arguments.
 * <p>
 * For example, the {@link lbot.task.Deadline} needs 3 arguments (command, description and due date).
 * If one of the 3 is not provided, this exception is thrown.
 */
// used when user inputs an invalid command i.e. incorrect number of arguments
public class InvalidCommandException extends LBotException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
