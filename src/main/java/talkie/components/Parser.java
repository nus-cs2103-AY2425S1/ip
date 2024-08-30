package talkie.components;

import talkie.command.*;
import talkie.exception.TalkieUnknownCommandException;

/**
 * Parses user input and returns the corresponding {@link Command} object.
 * This class is responsible for converting user input into specific command instances
 * based on the input command type.
 */
public class Parser {

    /**
     * Parses the given user input to determine the corresponding {@code Command} object.
     *
     * <p>This method extracts the command type from the user input and creates an instance
     * of the appropriate {@link Command} subclass based on the command type. If the command
     * type is not recognized, it throws a {@link TalkieUnknownCommandException}.</p>
     *
     * @param input The user input containing the command and its arguments.
     * @return A {@code Command} object corresponding to the user input.
     * @throws TalkieUnknownCommandException If the command type is not recognized.
     */
    public static Command getCommand(String input) throws TalkieUnknownCommandException {
        try {
            String inputCommand = input.split(" ", 2)[0];
            CommandType command = CommandType.valueOf(inputCommand.toUpperCase());

            switch (command) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand(input);
            case DELETE:
                return new DeleteCommand(input);
            case MARK:
                return new MarkCommand(input);
            case UNMARK:
                return new UnMarkCommand(input);
            case TODO:
                return new ToDoCommand(input);
            case DEADLINE:
                return new DeadlineCommand(input);
            case EVENT:
                return new EventCommand(input);
            case FIND:
                return new FindCommand(input);
            default:
                throw new TalkieUnknownCommandException(input);
            }
        } catch (IllegalArgumentException e) {
            throw new TalkieUnknownCommandException(input);
        }
    }
}
