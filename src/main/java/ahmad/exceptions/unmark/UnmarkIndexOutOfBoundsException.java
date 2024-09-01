package ahmad.exceptions.unmark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for out of bounds unmark index.
 */
public class UnmarkIndexOutOfBoundsException extends AhmadException {
    /**
     * Constructs an UnmarkIndexOutOfBoundsException instance based on given number.
     *
     * @param number The invalid index in question.
     */
    public UnmarkIndexOutOfBoundsException(String number) {
        super("\"" + number + "\" is out of bounds!");
    }
}
