package parser;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends OuiOuiBaguetteException {

    /**
     * Constructs an UnknownCommandException with a default error message.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
