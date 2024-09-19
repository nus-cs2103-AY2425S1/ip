package chacha.exception;

import java.time.DateTimeException;

/**
 * Represents a custom exception in response to wrong time format inputted in command.
 */
public class WrongTimeFormatException extends DateTimeException {
    private String msg;

    /**
     * Constructs a new WrongTimeFormatException with default error message.
     */
    public WrongTimeFormatException() {
        super("Please input a valid time in the format HH.MMam or HH.MMpm. ");
        this.msg = "Please input a valid time in the format HH.MMam or HH.MMpm (e.g. 10.22am). ";
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
