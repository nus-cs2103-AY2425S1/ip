package nimbus.exception;

import nimbus.ui.Ui;

/**
 * Exception for the wrong inputs that is not supported by user
 */
public class WrongInputException extends NimbusException {

    /**
     * Creates exception with message to guide user on how to create different tasks
     */
    public WrongInputException() {
        super("Sorry Nimbus don't understand what you are saying QwQ \n"
                + "Try using todo, deadline or event!" + Ui.HORIZONTAL_LINE);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
