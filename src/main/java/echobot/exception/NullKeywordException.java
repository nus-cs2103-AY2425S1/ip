package echobot.exception;

/**
 * Represents a NullKeywordException.
 */
public class NullKeywordException extends EchoBotException {
    public NullKeywordException() {
        super("Keyword cannot be left empty!");
    }

    public NullKeywordException(String message) {
        super(message);
    }
}
