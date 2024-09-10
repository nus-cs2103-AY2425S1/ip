package bean.exception;

public class UnknownCommandException extends Exception {

    public UnknownCommandException() {
        super("I don't get it. Did you type in the wrong command??");
    }

    public UnknownCommandException(String message) {
        super(message);
    }
}
