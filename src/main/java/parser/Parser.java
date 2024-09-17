package parser;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UndoCommand;
import commands.UnmarkCommand;
import exceptions.BrockException;

/**
 * Class to identify user commands, and create associated {@code Command} objects.
 */
public class Parser {
    private String getCommandType(String command) {
        String[] parts = command.split(" ", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Creates an associated {@code Command} object, based on the identified command type.
     *
     * @param command User command.
     * @return Associated command object.
     * @throws BrockException If user command is invalid.
     */
    public Command handleCommand(String command) throws BrockException {
        String commandType = this.getCommandType(command);

        // CHECKSTYLE.OFF: Indentation
        return switch (commandType) {
            case "bye" -> new ByeCommand(command);
            case "list" -> new ListCommand(command);
            case "mark" -> new MarkCommand(command);
            case "unmark" -> new UnmarkCommand(command);
            case "delete" -> new DeleteCommand(command);
            case "todo" -> new TodoCommand(command);
            case "deadline" -> new DeadlineCommand(command);
            case "event" -> new EventCommand(command);
            case "find" -> new FindCommand(command);
            case "undo" -> new UndoCommand(command);
            default -> throw new BrockException("Invalid command!");
        };
        // CHECKSTYLE.ON: Indentation
    }
}
