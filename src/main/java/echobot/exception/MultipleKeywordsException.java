package echobot.exception;

public class MultipleKeywordsException extends EchoBotException {
    public MultipleKeywordsException() {
        super("Cannot specify multiple keywords! Please enter only one keyword!");
    }

    public MultipleKeywordsException(String message) {
        super(message);
    }
}
