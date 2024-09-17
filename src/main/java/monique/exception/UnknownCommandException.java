package monique.exception;

/**
 * The <code>UnknownCommandException</code> class represents an exception that is thrown
 * when an unknown command is entered by the user. It extends the <code>MoniqueException</code>
 * class and provides specific advice related to unknown commands.
 */
public class UnknownCommandException extends MoniqueException {
    public static final String DEFAULT_MESSAGE = "You have entered an unknown command. Please find out available "
                                                 + "commands by using '/commands'";
    /**
     * Constructs a new <code>UnknownCommandException</code> with a default detail message.
     */
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
    public UnknownCommandException() {
        super(DEFAULT_MESSAGE);
    }
}
