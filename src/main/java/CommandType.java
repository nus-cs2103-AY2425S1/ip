public enum CommandType {
    EXIT("bye"),
    DISPLAY("list"),
    MARK_COMPLETE("mark"),
    MARK_INCOMPLETE("unmark"),
    DELETE("delete"),
    CREATE_TODO("todo"),
    CREATE_DEADLINE("deadline"),
    CREATE_EVENT("event");

    private final String commandType;

    private CommandType(String command) {
        this.commandType = command.toLowerCase();
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * Converts a text string into its relevant enum counterpart.
     * Throws an IllegalArgumentException if string does not match any enum values
     *
     * @param input the text containing an enum value
     * @return an enum
     */
    public static CommandType fromString(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.getCommandType().equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No command type found for: " + input);
    }
}
