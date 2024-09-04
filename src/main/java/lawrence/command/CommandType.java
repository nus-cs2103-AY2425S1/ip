package lawrence.command;

/**
 * Represents the different commands that can be issued by the user.
 */
public enum CommandType {
    EXIT("bye"),
    DISPLAY("list"),
    MARK_COMPLETE("mark"),
    MARK_INCOMPLETE("unmark"),
    DELETE("delete"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline"),
    ADD_EVENT("event");

    private final String commandType;

    /** Default constructor.
     * <p>
     * The input string is converted into lowercase for greater input flexibility.
     * </p>
     * @param command the string containing an enum value
     */
    private CommandType(String command) {
        this.commandType = command.toLowerCase();
    }

    /**
     * Returns the command type as a string.
     * @return a string of the command type
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Converts a text string into its relevant enum counterpart.
     *
     * @param input the text containing an enum value
     * @return      an enum type matching the input
     * @throws IllegalArgumentException if the input string does not match any enum values
     */
    public static CommandType fromString(String input) throws IllegalArgumentException {
        for (CommandType type : CommandType.values()) {
            if (type.getCommandType().equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("No command type found for: %s.", input));
    }
}
