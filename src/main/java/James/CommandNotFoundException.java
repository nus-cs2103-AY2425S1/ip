package james;

/**
 * Exception thrown when a command is not recognized.
 * This exception is used to indicate that the command provided does not match any known commands.
 */
class CommandNotFoundException extends JamesException {
    /**
     * Constructs a CommandNotFoundException with the specified detail message.
     *
     * @param command The unrecognised command.
     */

    public CommandNotFoundException(String command) {
        super("Sorry! I don't understand what you mean by (" + command + "). " +
                "Here are a list of commands i understand!\n" +
                "list\n" +
                "mark\n" +
                "unmark\n" +
                "todo\n" +
                "deadline\n" +
                "event\n" +
                "delete\n" +
                "find\n" +
                "bye");
    }
}
