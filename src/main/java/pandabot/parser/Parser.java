package pandabot.parser;

// used '*' import as all items are imported

import pandabot.commands.AddCommand;
import pandabot.commands.Command;
import pandabot.commands.DeleteCommand;
import pandabot.commands.ExitCommand;
import pandabot.commands.FindCommand;
import pandabot.commands.HelpCommand;
import pandabot.commands.ListCommand;
import pandabot.commands.MarkCommand;
import pandabot.commands.UnmarkCommand;
import pandabot.exceptions.InputException;

/**
 * Parses user input into commands that can be executed by the application.
 * This class interprets the user's input string and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the full command input string and returns the corresponding Command object.
     * The method splits the input into the command and its arguments, then creates the appropriate Command object.
     *
     * @param fullCommand the full user input string.
     * @return the corresponding Command object.
     * @throws InputException if the command is invalid or the input is incomplete.
     */
    public static Command parse(String fullCommand) throws InputException {
        assert fullCommand != null : "The input command should not be null";
        String[] commandParts = fullCommand.trim().split(" ", 2);
        assert commandParts.length > 0 : "There should be at least one word in the command";
        String command = commandParts[0].toLowerCase();
        Command parsedCommand;
        switch (command) {
        case "bye":
            parsedCommand = new ExitCommand();
            break;
        case "list":
            parsedCommand = new ListCommand();
            break;
        case "mark":
            parsedCommand = new MarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "unmark":
            parsedCommand = new UnmarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "delete":
            parsedCommand = new DeleteCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "todo":
            parsedCommand = new AddCommand("todo", commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "deadline":
            parsedCommand = new AddCommand("deadline", commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "event":
            parsedCommand = new AddCommand("event", commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "find":
            parsedCommand = new FindCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            break;
        case "help":
            parsedCommand = new HelpCommand();
            break;
        default:
            throw new InputException("Invalid command. Type 'help' for assistance.");
        }

        return parsedCommand;
    }
}
