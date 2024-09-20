package bot.exceptions;

/**
 * A <code>BotException</code> thrown when the provided task id is invalid.
 * For example, this could be a non-positive number, or non-number.
 *
 * @author mongj
 */
public class InvalidTaskIdException extends BotException {
    public InvalidTaskIdException(String id) {
        super(id + " is not a valid task ID");
    }
}
