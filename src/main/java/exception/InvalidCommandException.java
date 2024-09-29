package exception;

/**
 * Exception for an invalid command.
 *
 * @author dwsc37
 */
public class InvalidCommandException extends BotException {
    /**
     * Constructor for an <code>InvalidCommandException</code>.
     * @param input Command inputted by user.
     */
    public InvalidCommandException(String input) {
        super("Invalid command: " + input + "! Type 'help' to view all available commands.");
    }
}
