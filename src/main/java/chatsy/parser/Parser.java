package chatsy.parser;

import chatsy.commands.*;
import chatsy.exceptions.ChatsyException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.TaskManager;

/**
 * Parses user input and delegates execution to the appropriate command.
 */
public class Parser {

    private final TaskManager taskManager;

    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Parses the given command and returns the corresponding {@code Command} object.
     *
     * @param command The user input.
     * @return The command to be executed.
     * @throws InvalidCommandException If the command is invalid.
     */
    public Command parse(String command) throws ChatsyException {

        String[] parts = command.split(" ", 2);
        String action = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (action) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "sort":
            return new SortCommand(arguments);
        default:
            return new UnknownCommand(action, arguments);
        }
    }
}
