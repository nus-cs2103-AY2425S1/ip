package orion.orionexceptions;

/**
 * Exception thrown when the delete command format is incorrect.
 *
 * <p>
 * The expected format for a delete command is:
 * <code>delete &lt;task number&gt;</code>.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidDeleteException extends OrionException {

    /**
     * Creates a new InvalidDeleteException with a message showing the invalid input
     * and the correct format for the delete command.
     *
     * @param input the invalid input that triggered the exception.
     */
    public InvalidDeleteException(String input) {
        super("Your input was '" + input
                + "'. However, the correct way to use delete is: delete <task number>. For example: delete 1");
    }
}
