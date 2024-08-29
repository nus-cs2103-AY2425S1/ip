package james;
/**
 * Exception thrown when a command is not recognized.
 * This exception is used to indicate that the command provided does not match any known commands.
 */
public class CommandNotFoundException extends JamesException {
    /**
     * Constructs a CommandNotFoundException with the specified detail message.
     *
     * @param message The detail message.
     */
    public CommandNotFoundException(String message) {
        super(message);
    }
}
