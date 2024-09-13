package elara.utils;

import elara.command.AddCommand;
import elara.command.Command;
import elara.command.DeleteCommand;
import elara.command.ExitCommand;
import elara.command.FindCommand;
import elara.command.ListCommand;
import elara.command.MarkCommand;
import elara.command.UnmarkCommand;
import elara.task.InvalidInputException;

/**
 * The {@code Parser} class is responsible for interpreting user input and returning
 * the corresponding command. This class helps in determining what the user wants to do.
 */
public class Parser {
    public static String getCommandType(String input) {
        assert input != null;
        String[] parts = input.split(" ", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Parses the user's input and returns the appropriate command object.
     *
     * @param input The full input string from the user.
     * @return A {@code Command} object representing the user's desired action.
     * @throws InvalidInputException If the input does not match any known commands.
     */
    public static Command parse(String input) throws InvalidInputException {
        String command = getCommandType(input);
        return switch (command) {
        case "bye" -> new ExitCommand();
        case "find" -> new FindCommand(input);
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(input);
        case "unmark" -> new UnmarkCommand(input);
        case "delete" -> new DeleteCommand(input);
        case "todo", "deadline", "event" -> new AddCommand(command, input);
        default -> throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n"
                    + "Try one of our commands: list mark unmark bye deadline todo event");
        };
    }
}

