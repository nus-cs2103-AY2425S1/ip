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
import commands.UnmarkCommand;
import exceptions.BrockException;

/**
 * Class to identify user commands, and create associated {@code Command} objects.
 */
public class Parser {
    /**
     * Identifies the type of command given.
     *
     * @param command User command.
     * @param target Command type.
     * @return True if command matches the specified type. False otherwise.
     */
    private boolean isCommand(String command, String target) {
        String[] parts = command.toLowerCase()
                .split(" ");
        String firstWord = parts[0];
        return firstWord.equalsIgnoreCase(target);
    }

    /**
     * Creates an associated {@code Command} object, based on the identified command type.
     *
     * @param command User command.
     * @return Associated command object.
     * @throws BrockException If user command is invalid.
     */
    public Command handleCommand(String command) throws BrockException {
        if (this.isCommand(command, "bye")) {
            return new ByeCommand(command);

        } else if (this.isCommand(command, "list")) {
            return new ListCommand(command);

        } else if (this.isCommand(command, "mark")) {
            return new MarkCommand(command);

        } else if (this.isCommand(command, "unmark")) {
            return new UnmarkCommand(command);

        } else if (this.isCommand(command, "delete")) {
            return new DeleteCommand(command);

        } else if (this.isCommand(command, "todo")) {
            return new TodoCommand(command);

        } else if (this.isCommand(command, "deadline")) {
            return new DeadlineCommand(command);

        } else if (this.isCommand(command, "event")) {
            return new EventCommand(command);

        } else if (this.isCommand(command, "find")) {
            return new FindCommand(command);
        } else {
            throw new BrockException("Invalid command!");
        }
    }
}
