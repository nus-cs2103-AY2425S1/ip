package ahmad.exceptions.mark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for out of bounds mark index.
 */
public class MarkIndexOutOfBoundsException extends AhmadException {
    /**
     * Constructs a MarkIndexOutOfBoundsException instance based on given number.
     *
     * @param number The invalid number in question.
     */
    public MarkIndexOutOfBoundsException(String number) {
        super("\"" + number + "\" is out of bounds!");
    }
}
