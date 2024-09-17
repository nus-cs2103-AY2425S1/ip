package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;

public class IllegalEventTimesException extends IllegalArgumentException {
    public IllegalEventTimesException() {
        super(ERROR_EVENT_TIMES_INORDERED);
    }
}
