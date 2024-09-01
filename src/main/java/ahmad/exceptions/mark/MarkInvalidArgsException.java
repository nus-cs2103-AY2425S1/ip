package ahmad.exceptions.mark;

import ahmad.exceptions.AhmadException;

public class MarkInvalidArgsException extends AhmadException {
    public MarkInvalidArgsException() {
        super("That is not a valid \"mark\" command");
    }
}
