package ahmad.exceptions.mark;

import ahmad.exceptions.AhmadException;

public class MarkInvalidNumberException extends AhmadException {
    public MarkInvalidNumberException(String number) {
        super("\"" + number + "\" is an invalid number");
    }
}
