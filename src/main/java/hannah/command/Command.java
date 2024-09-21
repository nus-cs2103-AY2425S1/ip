package hannah.command;

/**
 * Represents a command with a command type and optional arguments.
 * The command can be used to specify actions like adding tasks, deleting tasks, or other operations.
 */
public class Command {
    private String commandType;

    /**
     * Constructs a Command object with only a command type.
     * This constructor is used when no arguments are needed for the command.
     *
     * @param commandType The type of command (e.g., "todo", "deadline", "event").
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Constructs a Command object with a command type and arguments.
     * This constructor is used when the command requires additional arguments.
     *
     * @param commandType The type of command (e.g., "todo", "deadline", "event").
     * @param arguments The arguments for the command (e.g., task details, task number).
     */
    public Command(String commandType, String arguments) {
        this.commandType = commandType;
    }

    /**
     * Gets the command type.
     *
     * @return The command type (e.g., "todo", "deadline", "event").
     */
    public String getCommandType() {
        return commandType;
    }
}
