package exception;

public class CasperBotInvalidCommandException extends CasperBotException {
    public CasperBotInvalidCommandException() {
        super("Invalid command", "Here are the list of valid commands: mark, unmark, delete, list, todo, event, deadline");
    }
}
