package nimbus.exception;

import nimbus.ui.Ui;

/**
 * Exception for missing description for any tasks
 */

public class MissingDescriptionException extends Exception {

    /**
     * Creates exceptions with message to remind users to include task descriptions
     * prints out task name for clarity
     *
     * @param taskName
     */

    public MissingDescriptionException(String taskName) {
        super("Oh noo, you cant leave the description of " + taskName +
                " empty!!" + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
