package ahmad.exceptions.delete;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid delete number;
 */
public class DeleteInvalidNumberException extends AhmadException {

    /**
     * Constructs a DeleteInvalidNumberException instance based on given number.
     *
     * @param number The invalid number in question.
     */
    public DeleteInvalidNumberException(String number) {
        super("\"" + number + "\" is an invalid number");
    }
}
