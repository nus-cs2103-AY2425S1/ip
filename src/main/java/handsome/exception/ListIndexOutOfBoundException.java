package handsome.exception;

/**
 * The ListIndexOutOfBoundException class is a custom exception that extends
 * HandsomeException and is thrown when an invalid index is accessed in the
 * task list within the Handsome chatBot application.
 * This exception indicates that the user has attempted to access a list index
 * that is outside the valid range of available tasks.
 */
public class ListIndexOutOfBoundException extends HandsomeException {
    public ListIndexOutOfBoundException(int size) {
        super("Invalid List Index! List has " + size + " items.");
    }
}
