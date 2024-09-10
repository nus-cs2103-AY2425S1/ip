package fanny.parser;

import fanny.FannyException;
import fanny.command.*;

/**
 * Parses user input and returns the matching command to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the matching {@code Command} object.
     *
     * @param input The user's input as a string.
     * @return The {@code Command} object representing the user's command.
     * @throws FannyException If the command is unknown.
     */
    public static Command parse(String input) throws FannyException {
        String[] cmdParts = input.split(" ", 2);
        String action = cmdParts[0];
        String arguments = cmdParts.length > 1 ? cmdParts[1] : "";

        switch (action) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "todo":
            return new ToDoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "remind":
            return new RemindCommand();
        default:
            throw new FannyException("Sorry, I'm confused! Please try again.");
        }
    }

}
