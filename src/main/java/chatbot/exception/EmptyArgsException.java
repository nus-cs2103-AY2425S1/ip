package chatbot.exception;

public class EmptyArgsException extends InputException {
    public EmptyArgsException() {
        super("The arguments cannot be empty!");
    }
}
