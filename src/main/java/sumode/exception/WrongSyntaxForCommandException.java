package sumode.exception;

import sumode.util.Command;

/**
 * An Exception thrown for wrong syntax of command
 */
public class WrongSyntaxForCommandException extends Exception {

    /**
     * Constructor for AlreadyMarked Exception
     * @param command The command which user tried to perform.
     */
    public WrongSyntaxForCommandException(Command command) {
        super("Sumo understood your command but dunno what you want! Please utilise \""
                + command
                + "\" the correct way."
                + "\n"
                + "The correct syntax is "
                + command.expectedFormat()
                + '.'
        );
    }
}
