package exceptions;

import util.Utility;

/**
 * Class for a deadline exception.
 */
public class DeadlineException extends MizzException {
    public DeadlineException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return Utility.INDENT + "Oh no dead deadline command: " + super.getMessage()
                + Utility.NEW_LINE + Utility.INDENT
                + "Example usage: deadline <description> /by <deadline>";
    }
}
