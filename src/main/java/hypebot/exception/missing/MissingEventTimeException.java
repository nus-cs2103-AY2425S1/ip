package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_EVENT_TIME_MISSING;

public class MissingEventTimeException extends MissingArgumentException {
    public MissingEventTimeException() {
        super(ERROR_EVENT_TIME_MISSING);
    }
}
