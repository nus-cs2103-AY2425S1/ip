package yap.task;

import yap.ui.InputException;

/**
 * An exception thrown by a wrong format of deadline input by the user.
 */
public class DeadlineException extends InputException {

    /**
     * The different causes of the deadline exception, to be implemented.
     */
    public enum ExceptionType {
    }

    /**
     * Constructs a DeadlineException with a generic message.
     */
    public DeadlineException() {
        super("The deadline format is wrong!\n" +
                "The correct format is:\n" +
                "deadline task_description /by yyyy-mm-dd");
    }
}
