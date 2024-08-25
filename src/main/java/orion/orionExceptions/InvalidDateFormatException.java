package orion.orionExceptions;

public class InvalidDateFormatException extends OrionException {
    public InvalidDateFormatException(String message) {
        super("Invalid date format: " + message + ". Please use 'dd/MM/yyyy HHmm'.");
    }
}
