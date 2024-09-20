package bot.exceptions;

/**
 * A <code>BotException</code> thrown when a task description
 * cannot be parsed due to malformed datetime.
 */
public class InvalidDatetimeException extends BotException {
    public InvalidDatetimeException(String dt) {
        super(dt + " is not a valid date/time");
    }
}
