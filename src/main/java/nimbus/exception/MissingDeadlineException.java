package nimbus.exception;

import nimbus.ui.Ui;

/**
 * Exception is thrown when the deadline is missing for DeadlineTasks
 */
public class MissingDeadlineException extends NimbusException {

    /**
     * Creates exception with message to remind user to include deadline and the keyword for deadline
     */
    public MissingDeadlineException() {
        super("Nimbus noticed that you did not include a deadline! Use /by!" + Ui.HORIZONTAL_LINE);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
