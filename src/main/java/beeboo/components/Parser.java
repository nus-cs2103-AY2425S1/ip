package beeboo.components;

import beeboo.command.AddCommand;
import beeboo.command.Command;
import beeboo.command.DeleteCommand;
import beeboo.command.ExitCommand;
import beeboo.command.FindCommand;
import beeboo.command.ListCommand;
import beeboo.command.MarkCommand;
import beeboo.command.UpdateCommand;
import beeboo.exception.InvalidCommandException;

/**
 * The Parser class is responsible for parsing user commands and creating corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the given command string and returns the appropriate Command object based on the command type.
     * The method trims the command, identifies the command type, and creates the relevant Command object.
     *
     * @param command the command string entered by the user
     * @return a Command object representing the parsed command
     * @throws InvalidCommandException if the command is invalid or cannot be parsed
     */
    public static Command parseCommand(String command) throws InvalidCommandException {
        command = command.trim();
        int index = command.indexOf(' ');
        if (index == -1) {
            switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException();
            }
        }
        String commandWord = command.substring(0, index).trim();
        String rest = command.substring(index).trim();
        switch (commandWord) {
        case "deadline":
            return new AddCommand("d", rest);
        case "todo":
            return new AddCommand("t", rest);
        case "event":
            return new AddCommand("e", rest);
        case "mark":
            return new MarkCommand("m", rest);
        case "unmark":
            return new MarkCommand("u", rest);
        case "delete":
            return new DeleteCommand(rest);
        case "find":
            return new FindCommand(rest);
        case "update":
            return new UpdateCommand(rest);
        default:
            throw new InvalidCommandException();
        }
    }
}
