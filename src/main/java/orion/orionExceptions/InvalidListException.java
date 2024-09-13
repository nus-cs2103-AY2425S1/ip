package orion.orionexceptions;

/**
 * Exception thrown when an invalid list command is provided.
 *
 * <p>
 * This exception is thrown when the input does not match the expected format
 * for listing tasks.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidListException extends OrionException {

    /**
     * Creates a new InvalidListException with a message showing the invalid input
     * and the correct way to use the list command.
     *
     * @param input the invalid input that triggered the exception.
     */
    public InvalidListException(String input) {
        super("Your input was " + input
                + ". However, the correct way to use list is to type 'list' to list out all the tasks.");
    }
}
