package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import command.UnknownCommand;
import command.UnmarkTaskCommand;
import exception.KukiShinobuException;

// Import all the Commands


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
     * creation by returning an {@link UnknownCommand}.
     * </p>
     *
     * @param fullCommand The full user input string to be parsed.
     * @return A {@code Command} object corresponding to the user input.
     */
    public static Command parse(String fullCommand) {
        // split user input into commands and argument (if applicable)
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        //prevents index out of bounds error if no arguments provided after command
        String arguments = parts.length > 1 ? parts[1] : "";
        try {
            // otherwise, handle all other commands as appropriate
            switch (command) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "find":
                    return new FindCommand(arguments);
                case "mark":
                    // argument is task index
                    return new MarkTaskCommand(arguments);
                case "unmark":
                    // argument is task index
                    return new UnmarkTaskCommand(arguments);
                case "delete":
                    // argument is task index
                    return new DeleteTaskCommand(arguments);
                case "todo":
                    // argument is desc, pass desc in
                    return new AddTodoCommand(arguments);
                case "deadline":
                    // Break arguments into desc + by
                    return new AddDeadlineCommand(arguments);
                case "event":
                    // break arguments into desc, start and end
                    return new AddEventCommand(arguments);
                default:
                    return new UnknownCommand();
            }
        } catch (KukiShinobuException e) {
            System.out.println(e.getMessage());
        }
        return new UnknownCommand();
    }

}
