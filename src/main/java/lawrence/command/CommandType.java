package lawrence.command;

import java.util.ArrayList;

/**
 * Represents the different commands that can be issued by the user.
 */
public enum CommandType {
    ADD_DEADLINE("deadline"),
    ADD_EVENT("event"),
    ADD_TODO("todo"),
    DELETE("delete"),
    DISPLAY("list"),
    EXIT("bye"),
    FIND_MATCHING("find"),
    INVALID("invalid"),
    MARK_COMPLETE("mark"),
    MARK_INCOMPLETE("unmark");

    private final String commandType;

    /**
     * Default constructor.
     * <p>
     * The input string is converted into lowercase for greater input flexibility.
     * </p>
     *
     * @param type the string containing an enum value
     */
    CommandType(String type) {
        this.commandType = type.toLowerCase();
    }

    /**
     * Converts a text string into its relevant enum counterpart. Full string matching
     * and partial string matching are both done.
     * <p>
     * If input does not match any known types, the {@link #INVALID} type
     * is returned.
     * IF the input matches multiple types, the {@link #INVALID} type is also returned.
     * </p>
     *
     * @param input the text containing an enum value
     * @return an enum type matching the input
     */
    public static CommandType fromString(String input) {
        ArrayList<CommandType> matches = new ArrayList<>();
        for (CommandType type : CommandType.values()) {
            // do not attempt to match with type INVALID as it is reserved to signify a failed parse
            if (type == INVALID) {
                continue;
            }

            String typeString = type.getCommandType();
            if (typeString.equalsIgnoreCase(input)) {
                matches.add(type);
                continue;
            }

            // Check for partial match
            if (typeString.startsWith(input.toLowerCase())) {
                matches.add(type);
            }
        }

        if (matches.size() != 1) {
            return INVALID;
        }
        return matches.get(0);
    }

    /**
     * Returns the command type as a string.
     *
     * @return a string of the command type
     */
    public String getCommandType() {
        return commandType;
    }
}
