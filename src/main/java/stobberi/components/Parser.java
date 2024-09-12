package stobberi.components;

import stobberi.command.Command;
import stobberi.command.DateCommand;
import stobberi.command.DeadlineCommand;
import stobberi.command.DeleteCommand;
import stobberi.command.EventCommand;
import stobberi.command.ExitCommand;
import stobberi.command.FindCommand;
import stobberi.command.HelpCommand;
import stobberi.command.ListCommand;
import stobberi.command.MarkCommand;
import stobberi.command.TodoCommand;
import stobberi.command.UnmarkCommand;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * This class is responsible for parsing user commands and creating the corresponding {@link Command} objects.
 * The {@code parse} method interprets the user's command string and returns the appropriate command object based
 * on the provided input. It throws specific exceptions for invalid or incomplete commands.
 */
public class Parser {

    // Private constructor to prevent instantiation of the Parser class
    private Parser() {}

    /**
     * Parses a user command and returns the corresponding {@link Command} object.
     *
     * @param fullCommand the full command string provided by the user
     * @param taskList the task list to be used for command operations
     * @return a {@link Command} object representing the user's command
     * @throws StobberiException if the command is invalid or incomplete
     * @throws NoNumberStobberiException if the command requires a number but it is missing
     * @throws NoSuchTaskStobberiException if the command does not match any known commands
     */
    public static Command parse(String fullCommand, TaskList taskList) throws StobberiException {
        String[] parts = fullCommand.split(" ");
        String command = parts[0];
        String restOfCommand = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));

        assert command != null : "Command is not a String";
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(taskList);
        case "mark":
            if (restOfCommand.matches("\\d+")) {
                int taskNumber = Integer.parseInt(restOfCommand);
                return new MarkCommand(taskList, taskNumber);
            } else {
                throw new NoNumberStobberiException("Where is the number?");
            }
        case "unmark":
            if (restOfCommand.matches("\\d+")) {
                int taskNumber = Integer.parseInt(restOfCommand);
                return new UnmarkCommand(taskList, taskNumber);
            } else {
                throw new NoNumberStobberiException("Where is the number?");
            }
        case "delete":
            if (restOfCommand.matches("\\d+")) {
                int taskNumber = Integer.parseInt(restOfCommand);
                return new DeleteCommand(taskList, taskNumber);
            } else {
                throw new NoNumberStobberiException("Where is the number?");
            }
        case "date":
            return new DateCommand(taskList, restOfCommand);
        case "todo":
            return new TodoCommand(taskList, restOfCommand);
        case "deadline":
            return new DeadlineCommand(taskList, restOfCommand);
        case "event":
            return new EventCommand(taskList, restOfCommand);
        case "find":
            return new FindCommand(taskList, restOfCommand);
        case "?":
            return new HelpCommand();
        default:
            throw new NoSuchTaskStobberiException("I'm sorry! That is NOT an executable command");
        }
    }
}
