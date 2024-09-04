package lawrence.parser;

import lawrence.command.Command;
import lawrence.command.CommandType;
import lawrence.command.DisplayTasksCommand;
import lawrence.command.ExitSessionCommand;
import lawrence.command.CompleteTaskCommand;
import lawrence.command.UncompleteTaskCommand;
import lawrence.command.DeleteTaskCommand;
import lawrence.command.AddTaskCommand;

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
     *
     * </p>
     * @param input                     the string containing a command to be parsed
     * @return                          a {@link Command} object
     * @throws IllegalArgumentException if the input cannot be parsed into a known command
     */
    public static Command createCommand(String input) throws IllegalArgumentException {
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
        case EXIT:
            c = new ExitSessionCommand();
            break;
        case DISPLAY:
            c = new DisplayTasksCommand();
            break;
        case MARK_COMPLETE:
            c = new CompleteTaskCommand(input);
            break;
        case MARK_INCOMPLETE:
            c = new UncompleteTaskCommand(input);
            break;
        case DELETE:
            c = new DeleteTaskCommand(input);
            break;
        case ADD_TODO:
            // Intentional falling through of case
        case ADD_DEADLINE:
            // Intentional falling through of case
        case ADD_EVENT:
            c = new AddTaskCommand(input);
            break;
        default:
            // This case should never be reached
            throw new IllegalStateException("Unexpected command: " + type);
        }
        return c;
    }
}
