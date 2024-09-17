package dumpling.command;

import dumpling.DumplingException;

/**
 * Enum of potential commands that Dumpling can accept
 */
public enum CommandEnum {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    BYE,
    FIND,
    NOTE;

    /**
     * Converts the given string into one of the CommandEnums.
     * Throws a DumplingException if provided string is not a CommandEnum
     * @param commandString String of command to be converted
     * @return CommandEnum corresponding to the string
     * @throws DumplingException Thrown when a command provided is not a CommandEnum
     */
    public static CommandEnum retrieveCommandEnum(String commandString) throws DumplingException {
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DumplingException(
                    String.format(
                            "    Grrr, %s is not a valid command for Dumpling to eat!\n"
                                    + "     To list items, use 'list'.\n"
                                    + "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n"
                                    + "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                            commandString));
        }
        return commandEnum;
    }
}
