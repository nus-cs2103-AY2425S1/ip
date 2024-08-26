package ip.derrick ;
public enum Commands {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN;

    /**
     * Convert the user input into a corresponding enum value.
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
