package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_HAPPENING_DATE_MISSING;

public class MissingSearchDateException extends MissingArgumentException {
    public MissingSearchDateException() {
        super(ERROR_HAPPENING_DATE_MISSING);
    }
}
