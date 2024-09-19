package chacha.exception;

import java.time.DateTimeException;

/**
 * Represents a custom exception in response to wrong date format inputted in command.
 */
public class WrongDateFormatException extends DateTimeException {
    private String msg;
    /**
     * Constructs a new WrongDateFormatException with default error message.
     */
    public WrongDateFormatException() {
        super("Please input the date in the format YYYY-MM-DD. ");
        this.msg = "Please input the date in the format YYYY-MM-DD. ";
    }

    /**
     * Returns the error message.
     *
     * @return String representation of the message
     */
    @Override
    public String toString() {
        return this.msg;
    }
}
