package assistinator;

/**
 * Represents command given to chat bot.
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, FIND, UNKNOWN;

    static Command fromString(String input) {
        String commandString = input.toUpperCase();
        try {
            return valueOf(commandString);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
