package broski;

public class InvalidDateTimeException extends IllegalArgumentException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
