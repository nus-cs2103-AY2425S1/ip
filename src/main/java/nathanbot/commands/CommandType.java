package nathanbot.commands;

/**
 * Enum representing different command types for NathanBot.
 * Javadocs using copilot
 */
public enum CommandType {
    /**
     * Command to break or exit.
     */
    BREAK("bye"),
    
    /**
     * Command to display the list.
     */
    DISPLAY_LIST("list"),
    
    /**
     * Command to mark an item as done.
     */
    MARK_DONE("mark "),
    
    /**
     * Command to mark an item as undone.
     */
    MARK_UNDONE("unmark "),
    
    /**
     * Command to add a todo item.
     */
    TODO("todo "),
    
    /**
     * Command to add a deadline item.
     */
    DEADLINE("deadline "),
    
    /**
     * Command to add an event item.
     */
    EVENT("event "),
    
    /**
     * Command to delete an item.
     */
    DELETE("delete "),
    
    /**
     * Unknown command type.
     */
    UNKNOWN("");

    private final String command;

    /**
     * Constructor for CommandType.
     *
     * @param command The command string associated with the command type.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Gets the command string associated with the command type.
     *
     * @return The command string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Determines the CommandType from the given input string.
     *
     * @param input The input string to match against command types.
     * @return The matching CommandType, or UNKNOWN if no match is found.
     */
    public static CommandType fromInput(String input) {
        for (CommandType type : values()) {
            if (input.equals(type.command) || input.startsWith(type.command)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}