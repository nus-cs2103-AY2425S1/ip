public class EdithException extends IllegalArgumentException {
    public EdithException(String message) {
        super(message + " Please provide a valid instruction with the correct relevant details.");
    }

    public EdithException(String message, int num) {
        super(message);
    }
}