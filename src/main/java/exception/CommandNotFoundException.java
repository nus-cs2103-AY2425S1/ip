package exception;

/**
 * Exception thrown when the command is not any of the command specified in the {@code Command} class
 */
public class CommandNotFoundException extends HyperionException {

    /**
     * Constructs a {@code CommandNotFoundException} with a detailed message constructed from the input
     *
     * @param input the invalid command that was passed in
     */
    public CommandNotFoundException(String input) {
        super(String.format("%s is not a valid command", input));
    }
}
