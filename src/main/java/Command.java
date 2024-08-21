/**
 * Enum representing the various commands that can be issued to the Mahesh bot.
 */
public enum Command {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    /**
     * Converts a string to its corresponding Command enum value.
     *
     * @param command The string representation of the command.
     * @return The corresponding Command enum value.
     * @throws MaheshException If the string does not match any Command enum value.
     */
    public static Command fromString(String command) throws MaheshException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MaheshException(command + " is not a valid command. Use the \"bye\" command if you wish to exit the bot.");
        }
    }
}