package Parser;

import Commands.*;
import Exceptions.BrockException;

/**
 * Class to identify user commands, and create associated command objects.
 */
public class Parser {
    /**
     * Identifies the type of command given.
     *
     * @param command User command.
     * @param target Command type.
     * @return True if command matches the specified type. False otherwise.
     */
    private boolean identifyCommand(String command, String target) {
        String[] parts = command.toLowerCase()
                .split(" ");
        String firstWord = parts[0];
        return firstWord.equalsIgnoreCase(target);
    }

    /**
     * Creates an associated command object, based on the identified command type.
     *
     * @param command User command.
     * @return Associated command object.
     * @throws BrockException If user command is invalid.
     */
    public Command handleCommand(String command) throws BrockException {
        if (identifyCommand(command, "bye")) {
            return new ByeCommand(command);
        }
        if (identifyCommand(command, "list")) {
            return new ListCommand(command);

        } else if (identifyCommand(command, "mark")) {
            return new MarkCommand(command);

        } else if (identifyCommand(command, "unmark")) {
            return new UnmarkCommand(command);

        } else if (identifyCommand(command, "delete")) {
            return new DeleteCommand(command);

        } else if (identifyCommand(command, "todo")) {
            return new TodoCommand(command);

        } else if (identifyCommand(command, "deadline")) {
            return new DeadlineCommand(command);

        } else if (identifyCommand(command, "event")) {
            return new EventCommand(command);

        } else {
            throw new BrockException("Invalid command!");
        }
    }
}
