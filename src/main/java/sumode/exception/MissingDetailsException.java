package sumode.exception;

import sumode.util.Command;

/**
 * An Exception thrown for missing details of taskings.
 */
public class MissingDetailsException extends Exception {

    /**
    * Constructor for MissingDetailsException
    * @param command The command which user tried to perform.
    */
    public MissingDetailsException(Command command) {
        super("Sumo understood your command but please provide more DETAILS! Please utilise \""
            + command
            + "\" the correct way."
            + "\n"
            + "The required syntax + details are "
            + command.expectedFormat()
            + '.'
        );
    }
}
