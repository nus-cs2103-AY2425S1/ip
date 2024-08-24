package sumode.exception;

import sumode.util.Command;

/**
 * An Exception thrown for inputing unknown command.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructor for AlreadyMarked Exception
     * @param command The unknown command which user tried to perform.
     */
    public UnknownCommandException(Command command) {
        super("Sumo dunno your command \"" + command + "\" ! Check spelling of your first word.");
    }
}
