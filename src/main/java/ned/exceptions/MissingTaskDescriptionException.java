package ned.exceptions;

public class MissingTaskDescriptionException extends NedException {
    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
