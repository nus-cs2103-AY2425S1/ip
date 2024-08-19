package exception;

/**
 * An exception for when user enter an invalid input.
 *
 * @author Toh Yi Hui A0259080A
 */
public class CommandNotFoundException extends BotException {
    public CommandNotFoundException(String input) {
        super("Command not found: " + input);
    }
}
