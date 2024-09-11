package command;

import exception.ElliotException;

/**
 * Enum class to hold every single possible command available to the user.
 */
public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    DELETE("delete", new DeleteCommand()),
    TODO("todo", new TodoCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    EVENT("event", new EventCommand()),
    BYE("bye", new ByeCommand()),
    FIND("find", new FindCommand());

    private final String commandString;
    private final Command command;

    CommandType(String commandString, Command command) {
        this.commandString = commandString;
        this.command = command;
    }

    /**
     * Returns the String representation of that command enum.
     */
    public String getCommandString() {
        return commandString;
    }

    /**
     * Returns the specific command class object of that command enum.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Attempts to match user input in the form of the string parameter to the command
     * type/class
     *
     * @param commandString string to match enum to
     */
    public static CommandType parseStringToCommand(String commandString) throws ElliotException {
        for (CommandType type : CommandType.values()) {
            if (type.getCommandString().equalsIgnoreCase(commandString)) {
                return type;
            }
        }
        throw new ElliotException("invalid command\n");
    }
}
