package ned.exceptions;

public class MissingTaskFromDateException extends NedException {
    public MissingTaskFromDateException(String errorMessage) {
        super(errorMessage);
    }
}
