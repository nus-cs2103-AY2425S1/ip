package michaelscott.command;

import michaelscott.utils.MichaelScottException;

/**
 * Represents a parser which takes a string input and returns the appropriate Command instance.
 * This class is responsible for interpreting user input and creating the corresponding Command objects.
 */
public class CommandParser {

    /**
     * Parses the given full command string and returns the appropriate Command instance.
     *
     * @param fullCommand The full command string to be parsed.
     * @return A Command object corresponding to the parsed command.
     * @throws MichaelScottException If the command is not recognized or cannot be parsed.
     */
    public Command parse(String fullCommand) throws MichaelScottException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command.toLowerCase()) {
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "delete" -> new DeleteCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "clear" -> new ClearlistCommand();
            case "find" -> new FindCommand(args);
            default -> throw new MichaelScottException("I don't understand what you mean to say!");
        };
    }
}
