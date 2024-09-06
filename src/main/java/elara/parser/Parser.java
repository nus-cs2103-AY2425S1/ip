package elara.parser;

import elara.command.Command;
import elara.command.AddCommand;
import elara.command.DeleteCommand;
import elara.command.ExitCommand;
import elara.command.ListCommand;
import elara.command.MarkCommand;
import elara.command.UnmarkCommand;;

import elara.task.InvalidInputException;

/**
 * Represents a parser that processes user input and determines the appropriate command to execute.
 * The Parser class is responsible for identifying the type of command and returning an instance of
 * the corresponding command class.
 */
public class Parser {
    /**
     * Extracts the command from the user's input
     * The command is the first word in the user's input.
     *
     * @param input The full input string provided by the user.
     * @return The command type in lowercase
     */
    public static String getCommandType(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     * The method determines the command type from the input string and returns an instance of the
     * appropriate Command class.
     * If the input command is not recognized, it throws an InvalidInputException.
     *
     * @param input input The full input string provided by the user.
     * @return The Command object corresponding to the user's input.
     * @throws InvalidInputException If the input command is not recognized.
     */
    public static Command parse(String input) throws InvalidInputException {
        String command = getCommandType(input);
        return switch (command) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "todo", "deadline", "event" -> new AddCommand(command, input);
            default -> throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n" +
                    "Try one of our commands: list mark unmark bye deadline todo event");
        };
    }
}
