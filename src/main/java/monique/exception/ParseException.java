package monique.exception;

/**
 * The <code>ParseException</code> class represents an exception that is thrown
 * when there is an error parsing a command, usually due to missing arguments.
 * It extends from <code>MoniqueException</code>.
 */
public class ParseException extends MoniqueException {
    public static final String DEFAULT_MESSAGE = "Parsing Exception. Please re-enter commands using the "
                                                 + "correct template. To find out "
                                                 + "command templates, please enter '/commands'";
    /**
     * The <code>ParseException</code> class represents an exception that is thrown
     * when there is an error parsing a command, usually due to missing arguments.
     * It extends from <code>MoniqueException</code>.
     */
    public ParseException() {
        super(DEFAULT_MESSAGE);
    }

    public ParseException(String errorMessage) {
        super(errorMessage);
    }
}
