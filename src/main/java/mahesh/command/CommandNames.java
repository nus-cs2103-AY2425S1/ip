package mahesh.command;

import mahesh.util.MaheshException;

/**
 * Enum representing the various commands that can be issued to the Mahesh bot.
 */
public enum CommandNames {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    /**
     * Converts a string to its corresponding Command enum value.
     *
     * @param command The string representation of the command.
     * @return The corresponding Command enum value.
     * @throws MaheshException If the string does not match any Command enum value.
     */
    public static CommandNames fromString(String command) throws MaheshException {
        try {
            return CommandNames.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MaheshException("Unknown command");
        }
    }
}
