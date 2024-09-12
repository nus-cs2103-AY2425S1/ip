package donna.parse;

/**
 * Represents a command parsed from user input.
 * This class encapsulates the type of command, and its associated arguments.
 */
public class ParsedCommand {
    private final String commandType;
    private final String argument1;
    private final String argument2;

    /**
     * Constructs a ParsedCommand with the specified command type and arguments.
     *
     * @param commandType The type of command (ex: "add").
     * @param argument1 The first argument associated with the command. Can be null.
     * @param argument2 The second argument associated with the command. Can be null.
     */
    public ParsedCommand(String commandType, String argument1, String argument2) {
        assert commandType != null && !commandType.trim().isEmpty() :
                "Command type should not be null or empty";

        this.commandType = commandType;
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    /**
     * Constructs a ParsedCommand with the specified command type and one argument.
     *
     * @param commandType The type of command (ex: "find", "add", "delete").
     * @param argument1 The first argument associated with the command. Can be null.
     */
    public ParsedCommand(String commandType, String argument1) {
        this(commandType, argument1, null);
    }

    /**
     * Constructs a ParsedCommand with the specified command type and no arguments.
     *
     * @param commandType The type of command (ex: "list", "exit").
     */
    public ParsedCommand(String commandType) {
        this(commandType, null, null);
    }

    public String getCommandType() {
        return commandType;
    }

    public String getArgument1() {
        return argument1;
    }

    public String getArgument2() {
        return argument2;
    }
}
