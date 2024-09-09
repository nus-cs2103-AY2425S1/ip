package monique.exception;

/**
 * The <code>MarkException</code> class represents an exception that is thrown
 * when an operation related to marking or unmarking tasks fails. It extends from <code>MoniqueException</code>.
 */
public class MarkException extends MoniqueException {


    /**
     * Constructs a new <code>MarkException</code> with a default detail message.
     * The message indicates that an item number related to marking is not allowed.
     */
    public MarkException() {
        super("Mark Exception: Item Number is not allowed");
    }

    /**
     * Provides advice on how to handle a mark-related exception.
     *
     * @return a string containing advice for handling the exception, indicating that the operation failed because the
     *      item either does not exist or is already in the desired marked/unmarked state.
     */
    @Override
    public String advice() {
        return "Mark-related Exception. You have tried to mark an item which does not exist, "
               + "or unmark something that is already unmarked.";
    }
}