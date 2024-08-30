package exceptions;

public class MissingDeadlineException extends Exception {
    public MissingDeadlineException(String message) {
        super(message);
    }
}