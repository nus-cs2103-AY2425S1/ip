package orion.orionexceptions;

/**
 * Exception thrown when an invalid mark or unmark command is provided.
 *
 * <p>
 * This exception is thrown when the input does not match the expected format
 * for marking or unmarking tasks.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidMarkException extends OrionException {

    /**
     * Creates a new InvalidMarkException with a message showing the invalid input
     * and the correct format for using the mark or unmark commands.
     *
     * @param input the invalid input that triggered the exception.
     */
    public InvalidMarkException(String input) {
        super("Your input was " + input
                + ". However, the correct way to use mark or unmark is to type 'mark 1' (or 'unmark 1') to mark (or unmark) the task listed by 1.");
    }
}
