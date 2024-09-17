package monique.exception;

/**
 * The <code>IllegalDateFormatException</code> class represents an exception that is thrown
 * when an invalid date format is encountered. It extends from <code>MoniqueException</code>.
 */
public class IllegalDateFormatException extends MoniqueException {
    public static final String DEFAULT_MESSAGE = "You have tried to create a task without using proper date"
                                                 + "-time formats. Please try again, with the correct input format";
    /**
     * Constructs a new <code>IllegalDateFormatException</code> with a default detail message.
     * The message indicates that the date format provided is illegal.
     */
    public IllegalDateFormatException() {
        super(DEFAULT_MESSAGE);
    }

}
