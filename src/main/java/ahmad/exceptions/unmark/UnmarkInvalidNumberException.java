package ahmad.exceptions.unmark;

import ahmad.exceptions.AhmadException;

public class UnmarkInvalidNumberException extends AhmadException {
    public UnmarkInvalidNumberException(String number) {
        super("\"" + number + "\" is an invalid number");
    }
}
