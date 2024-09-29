package exception;

/**
 * Exception for an invalid task index.
 *
 * @author dwsc37
 */
public class InvalidTaskIndexException extends BotException {
    /**
     * Constructor for an <code>InvalidTaskIndexException</code>
     * @param taskIndex Task index inputted by the user.
     */
    public InvalidTaskIndexException(String taskIndex) {
        super("Invalid task index: " + taskIndex + "!\nTask index must be a positive integer");
    }
}
