package chatbot.exception;

public class MissingEventToException extends InputException {
    public MissingEventToException() {
        super("The Bobby.Event needs a /to argument!");
    }
}
