package command;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when a command is not found.
 */
public class CommandNotFoundException extends OuiOuiBaguetteException {
    /**
     * Constructs a CommandNotFoundException with the specified detail message.
     *
     * @param msg The detail message that describes the error.
     */
    public CommandNotFoundException(String msg) {
        super(msg);
    }
}
