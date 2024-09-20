package nugget.exception;

public class InvalidEventFormatException extends NuggetException {
    public InvalidEventFormatException() {
        super("Error: The format for the event command is incorrect. Please use 'event <description> /from <start time> /to <end time>'.");
    }
}
