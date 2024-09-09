package ned.exceptions;

public class UnknownCommandException extends NedException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
