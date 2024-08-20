package exception;

public class UnknownCommandException extends EchoBotException {
    public UnknownCommandException() {
        super("OOPS!!! I don't know what this command means...");
    }

    public UnknownCommandException(String message) {
        super(message);
    }
}
