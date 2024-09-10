package lawrence.parser;

import lawrence.command.AddTaskCommand;
import lawrence.command.Command;
import lawrence.command.CommandType;
import lawrence.command.CompleteTaskCommand;
import lawrence.command.DeleteTaskCommand;
import lawrence.command.DisplayTasksCommand;
import lawrence.command.ExitSessionCommand;
import lawrence.command.FindTasksCommand;
import lawrence.command.UncompleteTaskCommand;

/**
 * This class is used to make sense of text input by the user.
 * <p>
 * The commands are translated from text into their relevant command object
 * counterparts as specified in {@link CommandType}.
 * </p>
 */
public class CommandParser {
    /**
     * Converts the provided input string into a relevant {@link Command} object.
     * <p>
     * The commands available are as specified in {@link CommandType} and are exhaustive.
     * Any input that cannot be properly passed into a Command instance will result in
     * an {@link IllegalArgumentException}.
     * </p>
     *
     * @param input the string containing a command to be parsed
     * @return a {@link Command} object
     * @throws IllegalArgumentException if the input cannot be parsed into a known command
     * @throws IllegalStateException if the parsed {@link CommandType} is not recognised
     */
    public static Command createCommand(String input) throws IllegalArgumentException, IllegalStateException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Command input cannot be empty!");
        }
        // Parse command at start of the line
        String[] inputComponents = input.split(" ", 2);

        // determine the type of command
        String commandString = inputComponents[0];
        CommandType type;
        type = CommandType.fromString(commandString);

        Command c;
        switch(type) {
        case ADD_EVENT:
            // Fallthrough
        case ADD_DEADLINE:
            // Fallthrough
        case ADD_TODO:
            c = new AddTaskCommand(type, input);
            break;
        case DELETE:
            c = new DeleteTaskCommand(type, input);
            break;
        case DISPLAY:
            c = new DisplayTasksCommand(type);
            break;
        case EXIT:
            c = new ExitSessionCommand(type);
            break;
        case FIND_MATCHING:
            c = new FindTasksCommand(type, input);
            break;
        case MARK_COMPLETE:
            c = new CompleteTaskCommand(type, input);
            break;
        case MARK_INCOMPLETE:
            c = new UncompleteTaskCommand(type, input);
            break;
        default:
            // This case should never be reached
            throw new IllegalStateException("Unexpected command: " + type);
        }
        return c;
    }
}
