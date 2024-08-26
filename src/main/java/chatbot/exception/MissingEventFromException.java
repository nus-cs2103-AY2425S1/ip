package chatbot.exception;

public class MissingEventFromException extends InputException {
    public MissingEventFromException() {
        super("The Bobby.Event needs a /from argument!");
    }
}
