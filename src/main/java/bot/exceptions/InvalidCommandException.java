package bot.exceptions;

/**
 * A <code>BotException</code> thrown when a user types
 * in an invalid command that cannot be parsed.
 *
 * @author mongj
 */
public class InvalidCommandException extends BotException {
    public InvalidCommandException(String cmd) {
        super("'" + cmd + "'" + " is not a valid command");
    }
}
