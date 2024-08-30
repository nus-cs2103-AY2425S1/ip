package duke;

/**
 * The {@code Parser} class is responsible for interpreting and parsing user commands.
 * It takes in a raw command string, determines the type of command, and returns a
 * {@code Command} object that encapsulates the command type and relevant details.
 */
public class Parser {

    /**
     * Constructs a new {@code Parser} object.
     * The constructor is provided to allow for future extensibility, although it currently
     * has no specific implementation.
     */
    public Parser() {
    }

    /**
     * Parses the input command string and returns a corresponding {@code Command} object.
     * <p>
     * This method identifies the type of command (e.g., "bye", "list", "mark", "delete", "find")
     * by comparing the input string to known command types. It then creates and returns a
     * {@code Command} object that includes the command type and any additional details required
     * for the command's execution.
     * </p>
     *
     * @param command The raw command string input by the user.
     * @return A {@code Command} object encapsulating the identified command type and any relevant details.
     */
    public static Command parse(String command) {
        if (command.strip().equalsIgnoreCase(CommandType.BYE.toString().toLowerCase())) {
            return new Command(CommandType.BYE.toString().toLowerCase(), "");
        }
        if (command.strip().equalsIgnoreCase(CommandType.LIST.toString().toLowerCase())) {
            return new Command(
                    CommandType.LIST.toString().toLowerCase(),
                    "");
        } else if (command.strip().toLowerCase().contains(CommandType.MARK.toString().toLowerCase())) {
            return new Command(
                    CommandType.MARK.toString().toLowerCase(),
                    command);
        } else if (command.strip().toLowerCase().contains(CommandType.DELETE.toString().toLowerCase())) {
            return new Command(
                    CommandType.DELETE.toString().toLowerCase(),
                    command);
        } else if (command.strip().toLowerCase().contains(CommandType.FIND.toString().toLowerCase())) {
            return new Command(
                    CommandType.FIND.toString().toLowerCase(),
                    command);
        } else {
            return new Command(command, command);
        }
    }
}
