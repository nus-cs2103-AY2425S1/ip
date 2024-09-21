package nugget.exception;

/**
 * Represents an exception that is thrown when the user provides an incorrect format
 * for the event command. The {@code InvalidEventFormatException} indicates
 * that the format for creating an event is invalid.
 */
public class InvalidEventFormatException extends NuggetException {

    /**
     * Constructs an {@code InvalidEventFormatException} with a predefined error message.
     * This message specifies the correct format for the event command.
     */
    public InvalidEventFormatException() {
        super("The format for the event command is incorrect. "
                + "Please use 'event <description> /from <start time> /to <end time>'.");
    }
}
