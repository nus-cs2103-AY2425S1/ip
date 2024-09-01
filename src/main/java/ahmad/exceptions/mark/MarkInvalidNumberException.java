package ahmad.exceptions.mark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid mark number.
 */
public class MarkInvalidNumberException extends AhmadException {
    /**
     * Constructs a MarkInvalidNumberException instance based on given number.
     *
     * @param number The invalid number in question.
     */
    public MarkInvalidNumberException(String number) {
        super("\"" + number + "\" is an invalid number");
    }
}
