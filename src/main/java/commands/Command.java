package commands;

/**
 * Represents the commands that the chatbot may receive.
 * Commands not recognised by the chatbot are labelled INVALID.
 */
public enum Command {
    MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT, INVALID, LIST, BYE, EXIT;

    /**
     * Returns the Command associated to the specified string.
     *
     * @param s the string to be translated into the Command.
     * @return Command.
     */
    public static Command stringToCommand(String s) {
        switch (s) {
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "list":
            return LIST;
        case "bye":
            return BYE;
        case "exit":
            return EXIT;
        default:
            return INVALID;
        }
    }
}
