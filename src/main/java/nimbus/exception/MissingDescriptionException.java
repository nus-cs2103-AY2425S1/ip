package nimbus.exception;

import nimbus.ui.Ui;

public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String taskName) {
        super("Oh noo, you cant leave the description of " + taskName +
                " empty!!" + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
