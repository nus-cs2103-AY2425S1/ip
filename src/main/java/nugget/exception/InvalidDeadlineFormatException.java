package nugget.exception;

/**
 * Represents an exception that is thrown when the user provides an incorrect format
 * for the deadline command. The {@code InvalidDeadlineFormatException} indicates
 * that the format for setting a deadline is invalid.
 */
public class InvalidDeadlineFormatException extends NuggetException {

    /**
     * Constructs an {@code InvalidDeadlineFormatException} with a predefined error message.
     * This message specifies the correct format for the deadline command.
     */
    public InvalidDeadlineFormatException() {
        super("The format for the deadline command is incorrect. "
                + "Please use 'deadline <description> /by <date>'.");
    }
}
