package assistinator;

/**
 * Represents command word in user input.
 */
public enum CommandWord {
    BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, FIND, UNKNOWN;

    static CommandWord parseCommandWord(String input) {
        String commandString = input.toUpperCase();
        try {
            return valueOf(commandString);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

}
