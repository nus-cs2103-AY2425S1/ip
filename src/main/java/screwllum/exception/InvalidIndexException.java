package screwllum.exception;

public class InvalidIndexException extends ScrewllumException {
    public InvalidIndexException(String index) {
        super("Your index " + index + " is out of bounds");
    }
}
