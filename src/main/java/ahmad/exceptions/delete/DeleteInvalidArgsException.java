package ahmad.exceptions.delete;

import ahmad.exceptions.AhmadException;

public class DeleteInvalidArgsException extends AhmadException {
    public DeleteInvalidArgsException() {
        super("That is not a valid \"delete\" command");
    }
}
