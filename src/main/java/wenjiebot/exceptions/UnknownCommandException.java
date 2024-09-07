package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user inputs an unrecognized command.
 * This exception provides a custom error message to inform the user about the valid commands.
 */
public class UnknownCommandException extends WenJieException {

    /**
     * Constructs an UnknownCommandException with a default error message.
     */
    public UnknownCommandException() {
        super("test");
    }

    /**
     * Returns a custom error message that lists the valid commands the bot can execute.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "gg bro idk what command you want me to do, i only got \n "
                + "'todo', 'event', 'deadline, 'list', 'delete', 'mark', 'unmark' and 'bye'";
    }
}
