package data.exception;

public class MissingFlagException extends InvalidArgumentException {
    public MissingFlagException(String message) {
        super(message);
    }

}
