package exceptions;

public class StartAfterEndException extends HimException {
    public StartAfterEndException() {
        super("Events need to end after they start!");
    }
}
