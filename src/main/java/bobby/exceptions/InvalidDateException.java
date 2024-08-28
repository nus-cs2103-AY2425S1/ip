package bobby.exceptions;

public class InvalidDateException extends BobbyException {
    public InvalidDateException() {
        super("Invalid date format. Please use yyyy-MM-dd.");
    }
}
