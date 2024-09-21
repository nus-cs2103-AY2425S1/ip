package nimbus.exception;

import nimbus.ui.Ui;

/**
 * Exception for missing start and end times for EventTasks
 */
public class MissingStartEndTimeException extends NimbusException {

    /**
     * creates exception with message to remind user to include missing component for eventTasks
     * specifies which component is missing
     *
     * @param missing information that is missing
     */
    public MissingStartEndTimeException(String missing) {
        super("Nimbus noticed that you did not include the " + missing + Ui.HORIZONTAL_LINE);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
