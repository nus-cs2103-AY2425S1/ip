package ned.exceptions;

public class MissingSearchTermException extends NedException {
    public MissingSearchTermException(String errorMessage) {
        super(errorMessage);
    }
}
