package bobby.command;


/**
 * The {@code Command} enum represents the set of valid commands that the application
 * can interpret and execute. Each enum constant corresponds to a specific action
 * that the user can request, such as adding a task, marking a task as done, or exiting the application.
 * <p>
 * This enum provides a method, {@code fromString}, to convert user input into the corresponding
 * {@code Command} enum constant. If the input string does not match any recognized command,
 * the {@code UNKNOWN} constant is returned.
 * </p>
 * <p>
 * This class helps in categorizing and processing user commands in a structured way, making the
 * input handling process in the application more efficient and easier to manage.
 * </p>
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND;

    /**
     *Returns the corresponding {@code Command} enum constant for a given input string.
     * The input string is expected to be a command word (e.g., "bye", "list", "mark").
     * If the input does not match any known command, {@code UNKNOWN} is returned.
     *
     * @param input the input string representing a command
     * @return the {@code Command} enum constant corresponding to the input string,
     *         or {@code UNKNOWN} if the input does not match any known command
     */
    public static Command fromString(String input) {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "find":
                return FIND;
            default:
                return UNKNOWN;
        }
    }
}
