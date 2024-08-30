package thebotfather.command;

import thebotfather.util.TheBotFatherException;

/**
 * Enumeration representing the list of possible commands in the application.
 */
public enum CommandList {
    MARK, UNMARK, DELETE, TODO, LIST, DEADLINE, EVENT, BYE, FIND;

    /**
     * Finds and returns the corresponding {@code CommandList} enum constant for the given command string.
     * <p>
     * The command string is converted to uppercase before matching.
     * </p>
     *
     * @param command The command string to be matched with an enum constant.
     * @return The corresponding {@code CommandList} enum constant.
     * @throws TheBotFatherException If the command string does not match any of the enum constants.
     */
    public static CommandList findCommand(String command) throws TheBotFatherException {
        try {
            return CommandList.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "\tUse \"bye\" if you want to exit the program");
        }
    }
}
