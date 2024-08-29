package exception;

public class CasperBotInvalidCommandException extends CasperBotException {
    /**
     * A custom exception for an invalid command input by the user
     */
    public CasperBotInvalidCommandException() {
        super("Invalid command", "Here are the list of valid commands: mark, unmark, delete, list, todo, event, deadline");
    }
}
