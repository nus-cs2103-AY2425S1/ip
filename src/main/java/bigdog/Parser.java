package bigdog;

/**
 * The Parser class is responsible for interpreting and parsing input commands.
 * It splits the input string into a command and its associated arguments.
 * If the command is valid, it is returned in lowercase form.
 * If the command is invalid, a BigdogException is thrown.
 */
public class Parser {

    // List of valid commands recognized by the application
    private static final String[] VALID_COMMANDS = {"bye", "list", "mark", "unmark", "delete", "todo",
        "deadline", "event", "find", "view"};

    /** Error message for unrecognized task types. */
    private static final String UNRECOGNISED_KEYWORDS_MESSAGE =
            "The only keywords I recognise are:\n"
                    + "1. todo\n"
                    + "2. deadline\n"
                    + "3. event\n"
                    + "4. mark\n"
                    + "5. unmark\n"
                    + "6. delete\n"
                    + "7. list\n"
                    + "8. find\n"
                    + "9. view\n"
                    + "10. bye";

    /**
     * Constructs a {@code Parser} object.
     */
    public Parser() {
        // Constructor can be omitted if not used
    }

    /**
     * Parses the input string into a command and its associated arguments.
     * The input string is split into two parts: the command and the rest of the string (arguments).
     * If the command is valid, it is returned in lowercase form along with the contents of the task.
     * If the command is invalid, a {@code BigdogException} is thrown.
     *
     * @param str the input string to be parsed; it contains a command and possibly other arguments.
     * @return an array of two strings: the command and the remaining arguments.
     * @throws BigdogException if the command is not recognized.
     */
    public static String[] parse(String str) throws BigdogException {
        String[] temp = str.split(" ", 2);
        if (temp.length == 1) {
            temp = new String[]{temp[0], "NA"};
        }
        String command = temp[0].toLowerCase();
        for (String validCommand : VALID_COMMANDS) {
            if (command.equals(validCommand)) {
                temp[0] = command;
                return temp;
            }
        }
        throw new BigdogException("Parse Error: Invalid Argument! " + UNRECOGNISED_KEYWORDS_MESSAGE);
    }

}
