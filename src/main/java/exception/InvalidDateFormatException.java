package exception;

/**
 * Exception for an invalid date format.
 *
 * @author dwsc37
 */
public class InvalidDateFormatException extends BotException {
    /**
     * Constructor for an <code>InvalidDateFormatException</code>
     * @param date Date inputted by the user.
     */
    public InvalidDateFormatException(String date) {
        super("Invalid date format: " + date + "! Date must be formatted as yyyy-mm-dd.");
    }
}
