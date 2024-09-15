package rainy.rainyexceptions;

public class InvalidDeadlineParametersException extends Exception {
    public InvalidDeadlineParametersException(String errorMessage) {
        super(errorMessage);
    }
}