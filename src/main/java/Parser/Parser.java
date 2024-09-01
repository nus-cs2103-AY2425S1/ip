package Parser;

import Commands.*;
import Exceptions.BrockException;

public class Parser {
    private boolean identifyCommand(String command, String target) {
        String[] parts = command.toLowerCase()
                .split(" ");
        String firstWord = parts[0];
        return firstWord.equalsIgnoreCase(target);
    }

    // Return true: loop continue, return false: loop break
    public Command handleCommand(String command) throws BrockException {
        if (command.equalsIgnoreCase("bye")) {
            return new ByeCommand(command);
        }
        if (command.equalsIgnoreCase("list")) {
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
