package bottleopener.command;

/**
 * The {@code Command} interface represents an executable command in the BottleOpener chatbot.
 */
public interface Command {

    /**
     * Executes the command and returns a result, typically in the form of a message or feedback to the user.
     *
     * @return A string containing the result of the command execution.
     */
    String runCommand();
}
