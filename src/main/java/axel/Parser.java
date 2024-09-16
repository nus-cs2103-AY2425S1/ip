package axel;

/**
 * The {@code Parser} class is responsible for parsing user input commands.
 * It translates the input strings into specific {@code Command} objects that can be executed by the application.
 */
public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";
    private static final String HELP_COMMAND = "help";

    /**
     * Parses the user input command and returns the corresponding {@code Command} object.
     *
     * @param command The user's input command as a string.
     * @return The corresponding {@code Command} object based on the user's input.
     * @throws AxelException If the command format is invalid or unrecognized.
     */
    public static Command parse(String command) throws AxelException {
        assert command != null : "Command input should not be null";
        command = command.trim(); // Remove any leading or trailing whitespace

        if (command.matches("^" + TODO_COMMAND + " .+")) {
            String taskName = command.substring(TODO_COMMAND.length()).trim();
            validateTaskName(taskName);
            return new AddCommand(new ToDoTask(taskName));

        } else if (command.matches("^" + DEADLINE_COMMAND + " .+ /by .+")) {
            String[] parts = command.split(" /by ", 2);
            validateCommandParts(parts, DEADLINE_COMMAND, 2);
            return new AddCommand(new DeadlineTask(parts[0].substring(DEADLINE_COMMAND.length()).trim(), parts[1].trim()));

        } else if (command.matches("^" + EVENT_COMMAND + " .+ /from .+ /to .+")) {
            String[] parts = command.split(" /from | /to ", 3);
            validateCommandParts(parts, EVENT_COMMAND, 3);
            return new AddCommand(new EventTask(parts[0].substring(EVENT_COMMAND.length()).trim(), parts[1].trim(), parts[2].trim()));

        } else if (command.matches("^" + MARK_COMMAND + " \\d+$")) {
            return new MarkCommand(parseTaskIndex(command));

        } else if (command.matches("^" + UNMARK_COMMAND + " \\d+$")) {
            return new UnmarkCommand(parseTaskIndex(command));

        } else if (command.matches("^" + DELETE_COMMAND + " \\d+$")) {
            return new DeleteCommand(parseTaskIndex(command));

        } else if (command.equalsIgnoreCase(LIST_COMMAND)) {
            return new ListCommand();

        } else if (command.equalsIgnoreCase(BYE_COMMAND)) {
            return new ExitCommand();

        } else if (command.matches("^" + FIND_COMMAND + " .+")) {
            String keyword = command.substring(FIND_COMMAND.length()).trim();
            if (keyword.isEmpty()) {
                throw new AxelException("The find command requires a search keyword.");
            }
            return new FindCommand(keyword);

        } else if (command.equalsIgnoreCase(HELP_COMMAND)) {
            return new HelpCommand();

        } else {
            throw new UnrecognisedCommandException();
        }
    }

    /**
     * Validates that the task name is not empty.
     *
     * @param taskName The task name to validate.
     * @throws AxelException If the task name is empty.
     */
    private static void validateTaskName(String taskName) throws AxelException {
        if (taskName.isEmpty()) {
            throw new AxelException("Task name cannot be empty.");
        }
    }

    /**
     * Validates that the command parts are present and correct.
     *
     * @param parts The parts of the command after splitting.
     * @param commandType The type of the command (for error messages).
     * @param expectedParts The expected number of parts.
     * @throws AxelException If the command parts are invalid.
     */
    private static void validateCommandParts(String[] parts, String commandType, int expectedParts) throws AxelException {
        if (parts.length < expectedParts) {
            throw new AxelException(commandType + " command should contain the required sections.");
        }
    }

    /**
     * Parses the task index from the user's input command.
     * The index is extracted and adjusted to be zero-based.
     *
     * @param command The user's input command containing the task index.
     * @return The zero-based index of the task.
     * @throws AxelException If the task number is not a valid integer.
     */
    private static int parseTaskIndex(String command) throws AxelException {
        try {
            return Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AxelException("Invalid task number.");
        }
    }
}
