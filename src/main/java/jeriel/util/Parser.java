package jeriel.util;
import jeriel.command.*;
import jeriel.task.*;
public class Parser {
    /**
     * Parses a command and returns the corresponding Command object.
     * 
     * @param fullCommand the full command to be parsed
     * @return the corresponding Command object
     * @throws JerielException if the command is invalid
     */
    public static Command parse(String fullCommand) throws JerielException {
        // Use assertion to ensure the input is valid
        assert fullCommand != null : "Command cannot be null";
        assert !fullCommand.trim().isEmpty() : "Command cannot be empty";
        
        // String[] commandAndArgs = fullCommand.split(" ", 2);
        String[] commandAndArgs = splitCommand(fullCommand);
        String command = commandAndArgs[0];
        String arguments = commandAndArgs.length > 1 ? commandAndArgs[1] : "";

        assert !command.isEmpty() : "Command part is empty after trimming";
        
        switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "find":
                return new FindCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "bye":
                return new ExitCommand();
            case "help":
                return new HelpCommand();
            default:
                throw new JerielException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Splits the input command into command and arguments.
     *
     * @param fullCommand the full user command
     * @return a string array containing command and arguments
     */
    private static String[] splitCommand(String fullCommand) {
        String[] commandAndArgs = fullCommand.trim().split(" ", 2);
        String command = commandAndArgs[0];
        String arguments = commandAndArgs.length > 1 ? commandAndArgs[1] : "";
        return new String[]{command, arguments};
    }
}
