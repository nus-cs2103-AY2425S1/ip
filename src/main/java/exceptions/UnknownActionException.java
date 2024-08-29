package exceptions;

public class UnknownActionException extends RuntimeException {
    public UnknownActionException(String msg) {
        super(msg);
    }
}
