package bruno.exceptions;

/**
 * Exception should be thrown when an unknown command is entered.
 */
public class UnknownCommandException extends BrunoException {
    public UnknownCommandException() {
        super("I did not understand, please tell me something I can comprehend");
    }
}
