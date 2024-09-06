package yap.task;

import yap.ui.InputException;

public class DeadlineException extends InputException {
    public enum ExceptionType {
    }

    /**
     * Constructs a DeadlineException with a generic message.
     */
    public DeadlineException() {
        super("The deadline format is wrong!");
    }
}
