package rainy.rainyexceptions;

public class InvalidEventParametersException extends Exception {
    public InvalidEventParametersException(String errorMessage) {
        super(errorMessage);
    }
}