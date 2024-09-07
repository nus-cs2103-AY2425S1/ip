package duke.MyException;

import duke.Ui;
import duke.Message;

/**
 * Thrown to indicate that an invalid date has been specified.
 * This exception is used to signal that the date provided by the user
 * does not conform to the expected format or is otherwise invalid.
 */
public class InvalidDateException extends Exception {
    /**
     * Constructs a new {@code InvalidDateException} with a default error message.
     */
    public InvalidDateException() {
        super(Ui.INDENT + Message.MyException.INVALID_DATE);
    }
}
