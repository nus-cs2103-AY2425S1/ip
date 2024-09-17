package monique.exception;

/**
 * The <code>DeleteException</code> class represents an exception that is thrown
 * when an error related to deleting an item occurs. It extends from <code>MoniqueException</code>.
 */
public class DeleteException extends MoniqueException {
    public static final String DEFAULT_MESSAGE = "Delete-related Exception. You have tried to delete "
                                                 + "an item which does not exist.";
    /**
     * Constructs a new <code>DeleteException</code> with a default detail message.
     * The message indicates that the item number is not allowed.
     */
    public DeleteException() {
        super(DEFAULT_MESSAGE);
    }
}
