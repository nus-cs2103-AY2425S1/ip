package nugget.exception;

public class InvalidDeadlineFormatException extends NuggetException {
    public InvalidDeadlineFormatException() {
        super("Error: The format for the deadline command is incorrect. Please use 'deadline <description> /by <date>'.");
    }
}
