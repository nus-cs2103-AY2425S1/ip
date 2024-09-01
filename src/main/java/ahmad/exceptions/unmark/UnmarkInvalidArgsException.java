package ahmad.exceptions.unmark;

import ahmad.exceptions.AhmadException;

public class UnmarkInvalidArgsException extends AhmadException {
    public UnmarkInvalidArgsException() {
        super("That is not a valid \"unmark\" command");
    }
}
