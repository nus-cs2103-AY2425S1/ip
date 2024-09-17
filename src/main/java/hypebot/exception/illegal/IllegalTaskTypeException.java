package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_INVALID_TASK_TYPE;

public class IllegalTaskTypeException extends IllegalArgumentException {
    public IllegalTaskTypeException(String message) {
        super(ERROR_INVALID_TASK_TYPE + message + "\n");
    }
}
