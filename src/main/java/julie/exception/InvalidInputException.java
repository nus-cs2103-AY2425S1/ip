package julie.exception;

public class InvalidInputException extends JulieException {
    public InvalidInputException(String message) {
        super(String.format("Oh no! There seems to be an error with the input for %s", message));
    }
}
