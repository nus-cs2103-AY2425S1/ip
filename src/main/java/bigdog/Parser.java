package bigdog;

/**
 * The Parser class is responsible for interpreting and parsing input commands.
 * It splits the input string into a command and its associated arguments.
 * If the command is valid, it is returned in lowercase form.
 * If the command is invalid, a BigdogException is thrown.
 */
public class Parser {

    // [command, description, start date, end date]
    private static final String[] VALID_COMMANDS = {"bye", "list", "mark", "unmark", "delete", "todo",
        "deadline", "event", "find"};

    /**
     * Constructs a {@code Parser} object.
     */
    public Parser() {

    }

    /**
     * Parses the input string into a command and its associated arguments.
     * The input string is split into two parts: the command and the rest of the string (arguments).
     * If the command is valid, it is returned in lowercase form along with the contents of the task.
     * If the command is invalid, a {@code BigdogException} is thrown.
     *
     * @param str the input string to be parsed.
     * @return an array of two strings: the command and the remaining arguments.
     * @throws BigdogException if the command is not recognized.
     */
    public static String[] parse(String str) throws BigdogException {
        String[] temp = str.split(" ", 2);
        String command = temp[0].toLowerCase();
        for (int i = 0; i < 9; i++) {
            if (command.equals(VALID_COMMANDS[i])) {
                break;
            }
            if (i == 8) {
                throw new BigdogException("Parse Error: Invalid Argument!");
            }
        }
        temp[0] = command;
        return temp;
    }

}
