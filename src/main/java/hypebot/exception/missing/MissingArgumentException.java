package hypebot.exception.missing;

public class MissingArgumentException extends IllegalArgumentException {
    public MissingArgumentException(String message) {
        super(message);
    }
}
