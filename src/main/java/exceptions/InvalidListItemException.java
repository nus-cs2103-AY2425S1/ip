package exceptions;

/**
 * Exception thrown when a requested list item does not exist in the task list.
 * This is a custom exception that extends {@link DelphiException}.
 */
public class InvalidListItemException extends DelphiException {

    /**
     * Constructs an {@code InvalidListItemException} with a message indicating the invalid item index.
     *
     * @param i The index of the item that is invalid or does not exist in the task list.
     */
    public InvalidListItemException(int i) {
        super("sorry, your task list doesn't have " + i + " items");
    }
}
