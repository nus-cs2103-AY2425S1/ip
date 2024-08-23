public enum Command {
    EXIT("bye"),
    DISPLAY("list"),
    MARK_COMPLETE("mark"),
    MARK_INCOMPLETE("unmark"),
    DELETE("delete"),
    CREATE_TODO("todo"),
    CREATE_DEADLINE("deadline"),
    CREATE_EVENT("event");

    private final String command;

    private Command(String command) {
        this.command = command.toLowerCase();
    }

    public String getCommand() {
        return command;
    }

    /**
     * Converts a text string into its relevant enum counterpart.
     * Throws an IllegalArgumentException if string does not match any enum values
     *
     * @param commandString the text containing an enum value
     * @return an enum
     */
    public static Command fromString(String commandString) {
        for (Command command : Command.values()) {
            if (command.getCommand().equalsIgnoreCase(commandString)) {
                return command;
            }
        }
        throw new IllegalArgumentException("No enum constant for command: " + commandString);
    }
}
