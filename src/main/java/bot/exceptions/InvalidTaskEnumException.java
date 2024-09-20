package bot.exceptions;

/**
 * A <code>BotException</code> thrown when a task enum cannot be parsed.
 *
 * @author mongj
 */
public class InvalidTaskEnumException extends BotException {
    public InvalidTaskEnumException(String e) {
        super("'" + e + "'" + " is not a valid enum for a task");
    }
}
