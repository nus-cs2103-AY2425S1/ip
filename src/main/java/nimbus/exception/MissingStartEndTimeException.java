package nimbus.exception;

import nimbus.ui.Ui;

/**
 * Exception for missing start and end times for EventTasks
 */

public class MissingStartEndTimeException extends Exception {

    /**
     * creates exception with message to remind user to include the missing component for eventTasks
     * specifies which component is missing
     *
     * @param missing
     */
    public MissingStartEndTimeException(String missing) {
        super("Nimbus noticed that you did not include the " + missing + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
