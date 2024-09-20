package luna;

import luna.command.Command;
import luna.command.DeadlineCommand;
import luna.command.DeleteCommand;
import luna.command.EventCommand;
import luna.command.ExitCommand;
import luna.command.FindCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.TodoCommand;
import luna.command.UndoCommand;
import luna.command.UnmarkCommand;

/**
 * Parser for inputs given by the user through the chatbot.
 */
public class Parser {
    enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        FIND,
        UNDO,
    }

    private static final String COMMAND_ERROR_MESSAGE = """
            Please enter one of the following commands:
            "todo", "deadline", "event" - add task of specified type to list
            "mark" - mark a task as completed
            "unmark" - unmark a task as not completed
            "delete" - remove task from current tasks
            "list" - show all tasks
            "find" - search for task given description
            "undo" - undo most recent command
            "bye" - close chatbot
            """;

    /**
     * Returns a Command to be executed.
     *
     * @param input command from user.
     * @return Command to be executed.
     * @throws LunaException If input has invalid format.
     */
    public static Command parse(String input, Command previousCommand) throws LunaException {

        if (input.isEmpty()) {
            throw new LunaException(COMMAND_ERROR_MESSAGE);
        }

        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LunaException("Invalid Command!\n" + COMMAND_ERROR_MESSAGE);
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand(previousCommand);

        case UNDO:
            return new UndoCommand();

        case MARK:
            return new MarkCommand(inputs, previousCommand);

        case UNMARK:
            return new UnmarkCommand(inputs, previousCommand);

        case DELETE:
            return new DeleteCommand(inputs, previousCommand);

        case FIND:
            return new FindCommand(inputs, previousCommand);

        case TODO:
            return new TodoCommand(inputs, previousCommand);

        case DEADLINE:
            return new DeadlineCommand(inputs, previousCommand);

        case EVENT:
            return new EventCommand(inputs, previousCommand);

        default:
            throw new LunaException(COMMAND_ERROR_MESSAGE);
        }
    }
}
