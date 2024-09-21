package nugget.exception;

/**
 * Represents an exception that is thrown when the user provides an invalid date format.
 * The {@code InvalidDateFormatException} indicates that the date format does not match
 * the expected format.
 */
public class InvalidDateFormatException extends NuggetException {

    /**
     * Constructs an {@code InvalidDateFormatException} with a predefined error message.
     * This message specifies the correct date format.
     */
    public InvalidDateFormatException() {
        super("The date format is incorrect. Please use 'yyyy-MM-dd HHmm'.");
    }
}
