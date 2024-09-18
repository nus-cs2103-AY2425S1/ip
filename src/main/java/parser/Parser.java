package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import command.UnknownCommand;
import command.UnmarkTaskCommand;
import exception.KukiShinobuException;

/**
 * Parses user input into executable commands.
 * <p>
 * The {@code Parser} class is responsible for interpreting user input and
 * converting it into appropriate {@link Command} objects. It handles various
 * commands by analyzing the input string and creating the corresponding command
 * instance.
 * </p>
 */
public class Parser {

    /**
     * Parses the given user input and returns the corresponding command.
     * <p>
     * This method splits the user input into the command and its arguments, then
     * maps the command to a specific {@link Command} subclass based on the
     * command keyword. It also handles any exceptions that occur during command
     * creation by returning an {@link InvalidCommand} with the error message.
     * </p>
     *
     * @param fullCommand The full user input string to be parsed.
     * @return A {@code Command} object corresponding to the user input, or an {@link InvalidCommand} if an error occurs.
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        try {
            switch (command) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "find":
                    return new FindCommand(arguments);
                case "mark":
                    return new MarkTaskCommand(arguments);
                case "unmark":
                    return new UnmarkTaskCommand(arguments);
                case "delete":
                    return new DeleteTaskCommand(arguments);
                case "todo":
                    return new AddTodoCommand(arguments);
                case "deadline":
                    return new AddDeadlineCommand(arguments);
                case "event":
                    return new AddEventCommand(arguments);
                default:
                    return new UnknownCommand();
            }
        } catch (KukiShinobuException e) {
            // In the event an error occurred during the instantiation of a Command,
            // possibly due to invalid format of arguments, a KukiShinobuException will be thrown, resulting in
            // an InvalidCommand being returned
            return new InvalidCommand(e.getMessage());
        }
    }
}
