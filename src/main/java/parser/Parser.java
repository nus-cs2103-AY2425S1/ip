package parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import commands.undo.UndoCommand;
import exceptions.BrockException;

/**
 * Class to identify user commands, and create associated {@code Command} objects.
 */
public class Parser {
    private static final Map<String, Function<String, Command>> COMMAND_OBJS = new HashMap<>();

    /**
     * Initializes the various constructors for each command type.
     */
    public Parser() {
        COMMAND_OBJS.put("bye", ByeCommand::new);
        COMMAND_OBJS.put("list", ListCommand::new);
        COMMAND_OBJS.put("mark", MarkCommand::new);
        COMMAND_OBJS.put("unmark", UnmarkCommand::new);
        COMMAND_OBJS.put("delete", DeleteCommand::new);
        COMMAND_OBJS.put("todo", TodoCommand::new);
        COMMAND_OBJS.put("deadline", DeadlineCommand::new);
        COMMAND_OBJS.put("event", EventCommand::new);
        COMMAND_OBJS.put("find", FindCommand::new);
        COMMAND_OBJS.put("undo", UndoCommand::new);
    }

    /**
     * Gets the command type from the user command.
     *
     * @param command User command.
     * @return Command type.
     */
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
        Function<String, Command> commandConstructor = COMMAND_OBJS.get(commandType);
        if (commandConstructor == null) {
            throw new BrockException("Unrecognized command!");
        }
        return commandConstructor.apply(command);
    }
}
