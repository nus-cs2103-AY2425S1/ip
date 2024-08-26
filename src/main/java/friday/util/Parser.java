package friday.util;

import static friday.command.CommandType.*;

import friday.command.*;

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
        CommandType commandType;
        try {
            commandType = valueOf(inputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FridayException("\tInvalid input. Please ensure that this command is supported by me" +
                    " and you have utilized the right syntax.\n\tCheck 'help' for more information.");
        }

        return switch (commandType) {
            case BYE -> new ByeCommand();
            case HELP -> {
                if (inputs.length > 1) {
                    throw new FridayException("\tInvalid input. 'help' command does not take any arguments.");
                }
                yield new HelpCommand();
            }
            case LIST -> {
                if (inputs.length > 1) {
                    throw new FridayException("\tInvalid input. 'list' command does not take any arguments.");
                }
                yield new ListCommand();
            }
            case MARK, UNMARK -> {
                if (inputs.length != 2) {
                    throw new FridayException("\tInvalid input. 'mark' and 'unmark' commands require exactly" +
                            " one argument.\n\tusage: mark <integer> || unmark <integer>");
                }
                yield new MarkUnmarkCommand(inputs);
            }
            case TODO -> {
                if (inputs.length < 2) {
                    throw new FridayException("\tInvalid input. 'todo' command requires a description." +
                            "\n\tusage: todo <string>");
                }
                yield new TodoCommand(fullCommand.substring(5));
            }
            case DEADLINE -> {
                if (inputs.length == 1) {
                    throw new FridayException("\tInvalid input. 'deadline' command requires a" +
                            " description and a deadline.\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>");
                }
                yield new DeadlineCommand(fullCommand.substring(9).split(" /by "));
            }
            case EVENT -> {
                if (inputs.length == 1) {
                    throw new FridayException("\tInvalid input. 'event' command requires a description," +
                            " start, and end time.\n\tusage: event <string> /from <yyyy-MM-dd HHmm>" +
                            " /to <yyyy-MM-dd HHmm>");
                }
                yield new EventCommand(fullCommand.substring(6).split(" /from | /to "));
            }
            case DELETE -> {
                if (inputs.length != 2) {
                    throw new FridayException("\tInvalid input. 'delete' command requires exactly one argument." +
                            "\n\tusage: delete <integer>");
                }
                yield new DeleteCommand(inputs);
            }
            default ->
                    throw new FridayException("Invalid input. Please ensure that this command is supported by me" +
                            " and you have utilized the right syntax.\n\tCheck 'help' for more information.");
        };
    }
}
