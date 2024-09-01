package ahmad.exceptions.event;

import ahmad.exceptions.AhmadException;

public class EventInvalidArgsException extends AhmadException {
    public EventInvalidArgsException() {
        super("That is not a valid \"event\" command");
    }
}
