package friday.command;

import friday.util.FridayException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding Command object.
     *
     * @param fullCommand The input string to be parsed.
     * @return The Command object corresponding to the input string.
     * @throws FridayException If the input string is invalid or not recognized.
     */
    public static Command parse(String fullCommand) throws FridayException {
        String[] inputs = fullCommand.split(" ");
        CommandType commandType = parseCommandType(inputs[0]);

        return createCommand(commandType, inputs, fullCommand);
    }

    /**
     * Parses the command type from the input string.
     *
     * @param command The first word of the input string, representing the command type.
     * @return The CommandType enum value.
     * @throws FridayException If the command type is invalid.
     */
    private static CommandType parseCommandType(String command) throws FridayException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FridayException("Invalid input. Please ensure that this command is supported by me"
                    + " and you have utilized the right syntax.\nCheck 'help' for more information.");
        }
    }

    /**
     * Creates and returns the appropriate Command object based on the command type.
     *
     * @param commandType The parsed command type.
     * @param inputs The split input string.
     * @param fullCommand The full input string.
     * @return The corresponding Command object.
     * @throws FridayException If there is an issue with the command's arguments.
     */
    private static Command createCommand(CommandType commandType, String[] inputs, String fullCommand)
            throws FridayException {
        return switch (commandType) {
        case BYE -> new ByeCommand();
        case HELP -> createHelpCommand(inputs);
        case LIST -> createListCommand(inputs);
        case MARK, UNMARK -> createMarkUnmarkCommand(inputs);
        case TODO -> createTodoCommand(fullCommand);
        case DEADLINE -> createDeadlineCommand(fullCommand);
        case EVENT -> createEventCommand(fullCommand);
        case DELETE -> createDeleteCommand(inputs);
        case FIND -> new FindCommand(inputs);
        case UPDATE -> createUpdateCommand(inputs);
        default -> throw new FridayException("Invalid input. Please ensure that this command is supported by me"
                + " and you have utilized the right syntax.\nCheck 'help' for more information.");
        };
    }

    private static Command createHelpCommand(String[] inputs) throws FridayException {
        if (inputs.length > 1) {
            throw new FridayException("Invalid input. 'help' command does not take any arguments.");
        }
        return new HelpCommand();
    }

    private static Command createListCommand(String[] inputs) throws FridayException {
        if (inputs.length > 1) {
            throw new FridayException("Invalid input. 'list' command does not take any arguments.");
        }
        return new ListCommand();
    }

    private static Command createMarkUnmarkCommand(String[] inputs) throws FridayException {
        if (inputs.length != 2) {
            throw new FridayException("Invalid input. 'mark' and 'unmark' commands require exactly one argument."
                    + "\nusage: mark <integer> || unmark <integer>");
        }
        return new MarkUnmarkCommand(inputs);
    }

    private static Command createTodoCommand(String fullCommand) throws FridayException {
        if (fullCommand.split(" ").length < 2) {
            throw new FridayException("Invalid input. 'todo' command requires a description."
                    + "\nusage: todo <string>");
        }
        return new TodoCommand(fullCommand.substring(5));
    }

    private static Command createDeadlineCommand(String fullCommand) throws FridayException {
        if (fullCommand.split(" ").length == 1) {
            throw new FridayException("Invalid input. 'deadline' command requires a description and a deadline."
                    + "\nusage: deadline <string> /by <yyyy-MM-dd HHmm>");
        }
        return new DeadlineCommand(fullCommand.substring(9).split(" /by "));
    }

    private static Command createEventCommand(String fullCommand) throws FridayException {
        if (fullCommand.split(" ").length == 1) {
            throw new FridayException("Invalid input. 'event' command requires a description, start, and end time."
                    + "\nusage: event <string> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        return new EventCommand(fullCommand.substring(6).split(" /from | /to "));
    }

    private static Command createDeleteCommand(String[] inputs) throws FridayException {
        if (inputs.length != 2) {
            throw new FridayException("Invalid input. 'delete' command requires exactly one argument."
                    + "\nusage: delete <integer>");
        }
        return new DeleteCommand(inputs);
    }

    private static Command createUpdateCommand(String[] inputs) throws FridayException {
        if (inputs.length < 3) {
            throw new FridayException("Invalid input. 'update' command requires an index and the new values."
                    + "\nusage: update <index> <fields>");
        }
        return new UpdateCommand(inputs);
    }
}
