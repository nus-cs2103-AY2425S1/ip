package nimbus.exception;

import nimbus.ui.Ui;

public class MissingStartEndTimeException extends Exception {

    private String missing;

    public MissingStartEndTimeException(String missing) {
        super("Nimbus.Nimbus noticed that you did not include the " + missing + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
