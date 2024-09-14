package ip.derrick;

/**
 * Contains the list of commands that the chatbot can execute.
 */
public enum Commands {

    BYE, LIST, MARK, UNMARK, FIND, DELETE, TODO, DEADLINE, EVENT, UNKNOWN;

    /**
     * Returns the corresponding enum value for the command.
     *
     * @param command The String input entered by the user, which represents a command.
     * @return A corresponding enum value indicating the command.
     */
    public static Commands fromString(String command) {
        try {
            return Commands.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
