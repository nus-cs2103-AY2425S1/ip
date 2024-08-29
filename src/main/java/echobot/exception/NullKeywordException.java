package echobot.exception;

public class NullKeywordException extends EchoBotException {
    public NullKeywordException() {
        super("Keyword cannot be left empty!");
    }

    public NullKeywordException(String message) {
        super(message);
    }
}
