package ahmad.exceptions.delete;

import ahmad.exceptions.AhmadException;

public class DeleteInvalidNumberException extends AhmadException {
    public DeleteInvalidNumberException(String number) {
        super("\"" + number + "\" is an invalid number");
    }
}
