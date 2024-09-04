package nimbus.exception;

import nimbus.ui.Ui;

public class MissingDeadlineException extends Exception {


    public MissingDeadlineException() {
        super("Nimbus noticed that you did not include a deadline! Use /by!" + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
