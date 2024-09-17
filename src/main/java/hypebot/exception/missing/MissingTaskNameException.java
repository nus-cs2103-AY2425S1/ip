package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_TASK_NAME_EMPTY;

public class MissingTaskNameException extends MissingArgumentException {
    public MissingTaskNameException() {
        super(ERROR_TASK_NAME_EMPTY);
    }
}
