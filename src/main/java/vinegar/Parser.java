package vinegar;

import vinegar.command.*;

/**
 * Parses user commands and returns the appropriate Command object.
 * <p>
 * This class handles converting raw user input into actionable commands, ensuring that
 * the correct Command object is returned based on the user's input.
 */
public class Parser {

    /**
     * Parses the user's full command and returns the corresponding Command object.
     *
     * @param fullCommand The complete input from the user.
     * @return The Command object corresponding to the user's input.
     * @throws VinegarException If the command is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws VinegarException {
        String[] inputParts = fullCommand.split(" ", 2);
        String instruction = inputParts[0].toLowerCase();
        String errorMessage = "Please use these commands: todo, deadline, event, list, mark, unmark, "
                + "bye, help";

        switch (instruction) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(inputParts);
            case "unmark":
                return new UnmarkCommand(inputParts);
            case "todo":
                return new AddTodoCommand(inputParts);
            case "deadline":
                return new AddDeadlineCommand(inputParts);
            case "event":
                return new AddEventCommand(inputParts);
            case "delete":
                return new DeleteCommand(inputParts);
            case "find":
                return new FindCommand(inputParts);
            case "help":
                return new HelpCommand();
            default:
                throw new VinegarException(errorMessage);
        }
    }
}
