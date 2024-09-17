package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_TASK_MARK_INVALID;

public class IllegalTaskStatusException extends IllegalArgumentException {
    public IllegalTaskStatusException(String message) {
        super(ERROR_TASK_MARK_INVALID + message + "\n");
    }
}
