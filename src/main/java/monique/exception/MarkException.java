package monique.exception;

/**
 * The <code>MarkException</code> class represents an exception that is thrown
 * when an operation related to marking or unmarking tasks fails. It extends from <code>MoniqueException</code>.
 */
public class MarkException extends MoniqueException {
    public static final String DEFAULT_MESSAGE = "Mark-related Exception. You have tried to mark an item which"
                                                 + "does not exist, or unmark something that is already unmarked.";
    /**
     * Constructs a new <code>MarkException</code> with a default detail message.
     * The message indicates that an item number related to marking is not allowed.
     */
    public MarkException() {
        super(DEFAULT_MESSAGE);
    }
}