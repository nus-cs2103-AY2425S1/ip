package Joseph;

import Joseph.Exceptions.InsufficientDetailsException;
import Joseph.Exceptions.UnknownCommandException;

/**
 *  The class responsible for interpreting user input commands.
 */
public class Parser {

    /**
     * The enum representing the possible commands supported by the chatbot.
     * Excludes the "bye" and "help" command, which are in the main class.
     */
    public enum Command {
        EXIT("bye"),
        LIST("list"),
        HELP("help"),
        MARK("mark "),
        UNMARK("unmark "),
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event "),
        DELETE("delete "),
        FIND("find ");

        private final String commandText;

        Command(String commandText) {
            this.commandText = commandText;
        }

        public String getCommandText() {
            return commandText;
        }
    }

    /**
     * Parses the user input to determine which command it is.
     * @param input The user input.
     * @return The respective Command enum.
     * @throws UnknownCommandException If the command is not in the Command enum.
     */
    public Command parseCommand(String input) throws UnknownCommandException {
        for (Command command : Command.values()) {
            if (input.startsWith(command.getCommandText())) {
                return command;
            }
        }
        throw new UnknownCommandException("That is not a recognised command!");
    }

    /**
     * Parses the task number from the input string.
     * @param input The user input.
     * @param commandText The command text to be trimmed. Either "mark ", "unmark ", or "delete ".
     * @return The task number.
     * @throws InsufficientDetailsException If the task number is not provided or invalid.
     */
    public int parseTaskNumber(String input, String commandText)
            throws InsufficientDetailsException {
        try {
            return Integer.parseInt(input.substring(commandText.length()).trim());
        } catch (NumberFormatException e) {
            throw new InsufficientDetailsException("I need a task number!");
        }
    }

    /**
     * Parse the details of the ToDo task from the input string.
     * @param input The user input.
     * @param commandText The command text to be trimmed. In this case, "todo ".
     * @return The ToDo details.
     * @throws InsufficientDetailsException If the description is missing
     */
    public String parseTodoDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String details = input.substring(commandText.length()).trim();
        if (details.isEmpty()) {
            throw new InsufficientDetailsException("I need a description for a todo!");
        }
        return details;
    }

    /**
     * Parse the details of the Deadline task from the input string.
     * @param input The user input.
     * @param commandText The command text to be trimmed. In this case, "deadline ".
     * @return An array containing the description and the due date
     * @throws InsufficientDetailsException If description or due date is missing
     */
    public String[] parseDeadlineDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String[] details = input.substring(commandText.length()).trim().split(" /");
        if (details.length < 2) {
            throw new InsufficientDetailsException("You need to provide a description "
                    + "and a due date for a deadline!");
        }
        return details;
    }

    /**
     * Parse the details of the Event task from the input string.
     * @param input The user input.
     * @param commandText The command text to be trimmed. In this case, "event ".
     * @return An array containing the description, the start and the end.
     * @throws InsufficientDetailsException If description, start or end are missing.
     */
    public String[] parseEventDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String[] details = input.substring(commandText.length()).trim().split(" /");
        if (details.length < 3) {
            throw new InsufficientDetailsException("You need to provide a description, "
                    + "a start and an end for an event!");
        }
        return details;
    }

    /**
     * Parse the keyword from the input string.
     * @param input The user input.
     * @param commandText The command text to be trimmed. In this case, "find ".
     * @return The keyword to search the list with.
     * @throws InsufficientDetailsException If keyword is missing.
     */
    public String parseFindKeyword(String input, String commandText)
            throws InsufficientDetailsException {
        String keyword = input.substring((commandText.length())).trim();
        if (keyword.isEmpty()) {
            throw new InsufficientDetailsException("You need to provide a keyword!");
        }
        return keyword;
    }
}
