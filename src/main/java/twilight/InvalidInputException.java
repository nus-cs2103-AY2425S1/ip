package twilight;

public class InvalidInputException extends Exception {
    private final String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
