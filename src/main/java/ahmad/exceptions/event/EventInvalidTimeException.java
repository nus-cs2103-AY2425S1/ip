package ahmad.exceptions.event;

import ahmad.exceptions.AhmadException;

public class EventInvalidTimeException extends AhmadException {
    public EventInvalidTimeException(String time) {
        super(time + " is not a valid event time");
    }
}
