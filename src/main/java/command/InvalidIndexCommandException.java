package command;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when an invalid index is encountered.
 */
public class InvalidIndexCommandException extends OuiOuiBaguetteException {

    /**
     * Constructs an InvalidIndexCommandException with supplied error message.
     */
    public InvalidIndexCommandException(String msg) {
        super(msg);
    }
}
