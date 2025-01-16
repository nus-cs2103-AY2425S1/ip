package exception;

/**
 * A custom exception for an invalid command input by the user
 */
public class CasperBotInvalidCommandException extends CasperBotException {
    /**
     * Constructor for the invalid command exception
     */
    public CasperBotInvalidCommandException() {
        super("Invalid command",
                "Here are the list of valid commands: mark, unmark, delete, list, todo, event, deadline, view");
    }
}
