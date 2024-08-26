package exception;

public class InvalidDeadlineFormatException extends EchoBotException {
    public InvalidDeadlineFormatException() {
        super("Deadline format is not valid!");
    }

    public InvalidDeadlineFormatException(String message) {
        super("Invalid deadline format! Please follow " + message + "!");
    }
}
