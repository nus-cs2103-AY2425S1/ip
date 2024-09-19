package command;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends OuiOuiBaguetteException {

    /**
     * Constructs an UnknownCommandException with supplied error message.
     */
    public UnknownCommandException(String msg) {
        super(msg);
    }
}
