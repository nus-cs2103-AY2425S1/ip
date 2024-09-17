package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_MISSING;

public class MissingDueDateException extends MissingArgumentException {
    public MissingDueDateException() {
        super(ERROR_DEADLINE_DATE_MISSING);
    }
}
