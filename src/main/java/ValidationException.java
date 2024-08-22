public class ValidationException extends OrionException {
    public ValidationException(String message) {
        super("Validation Error: " + message);
    }
}
