package bot.exceptions;

/**
 * A <code>BotException</code> thrown when a task description cannot be parsed.
 */
public class InvalidTaskDescriptionException extends BotException {
    public InvalidTaskDescriptionException(String msg) {
        super("Task description is invalid: " + msg);
    }
}
