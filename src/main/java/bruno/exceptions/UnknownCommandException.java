package bruno.exceptions;

/**
 * Exception should be thrown when an unknown command is entered.
 */
public class UnknownCommandException extends BrunoException {
    public UnknownCommandException() {
        super("Sorry, my brain couldn't comprehend such complex words.");
    }
}
