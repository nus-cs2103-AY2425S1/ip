package nimbus.exception;

import nimbus.ui.Ui;

public class WrongInputException extends Exception {
    public WrongInputException() {
        super("Sorry Nimbus.Nimbus don't understand what you are saying QwQ \n" +
                "Try using todo, deadline or event!" + Ui.horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
