package cloud.util;

import cloud.command.Command;
import cloud.command.CommandType;
import cloud.command.DeadlineCommand;
import cloud.command.DeleteCommand;
import cloud.command.EventCommand;
import cloud.command.FindCommand;
import cloud.command.HelpCommand;
import cloud.command.ListCommand;
import cloud.command.MarkCommand;
import cloud.command.TodoCommand;
import cloud.command.UnmarkCommand;
import cloud.exception.CloudException;
import cloud.exception.UnrecognisedCommandException;

/**
 * Parses user input into command objects.
 * <p>
 * The <code>Parser</code> class interprets user commands from a string input and creates
 * corresponding command objects that represent the operations to be performed.
 * </p>
 */
public class Parser {

    /**
     * Parses the input string into a Command object.
     *
     * @param input the user input string
     * @return a Command object representing the parsed command
     * @throws CloudException if the input is unrecognized or invalid
     */
    public Command parse(String input) throws CloudException {
        assert input != null : "Input cannot be null";
        String[] split = input.split(" ", 2);
        assert split.length > 0 : "Input must contain at least a command";
        String command = split[0].strip().toLowerCase();
        String body = "";
        if (split.length > 1) {
            body = split[1];
        }
        switch (command) {
        case "list":
            return parseList(body);
        case "todo":
            return parseTodo(body);
        case "deadline":
            return parseDeadline(body);
        case "event":
            return parseEvent(body);
        case "delete":
            return parseDelete(body);
        case "mark":
            return parseMark(body);
        case "unmark":
            return parseUnmark(body);
        case "find":
            return parseFind(body);
        case "help":
            return parseHelp(body);
        default:
            throw new UnrecognisedCommandException("Unrecognised command");
        }
    }

    /**
     * Parses a list command into a ListCommand object
     *
     * @return a ListCommand object
     */
    private Command parseList(String body) throws CloudException {
        if (!body.isBlank()) {
            throw new UnrecognisedCommandException();
        }
        return new ListCommand();
    }

    /**
     * Parses a todo command into a TodoCommand object.
     *
     * @param body the command body (description of the todo task)
     * @return a TodoCommand object
     * @throws CloudException if the description is empty
     */
    private Command parseTodo(String body) throws CloudException {
        if (body.isBlank()) {
            throw new UnrecognisedCommandException("Add a description to you task and try again");
        }
        return new TodoCommand(body);
    }

    /**
     * Parses a deadline command into a DeadlineCommand object.
     *
     * @param body the command body (description and due date of the deadline task)
     * @return a DeadlineCommand object
     * @throws CloudException if the command body does not contain "/by" or the date format is invalid
     */
    private Command parseDeadline(String body) throws CloudException {
        if (!body.contains("/by")) {
            throw new UnrecognisedCommandException();
        }
        String[] details = body.split("/by");
        String description = details[0].strip();
        DateTime due = DateTime.of(details[1].strip());
        return new DeadlineCommand(description, due);
    }

    private Command parseEvent(String body) throws CloudException {
        if (!body.contains("/from") || !body.contains("/to")) {
            throw new UnrecognisedCommandException();
        }
        String[] details = body.split("/from|/to");
        String description = details[0];
        DateTime startDate = DateTime.of(details[1]);
        DateTime endDate = DateTime.of(details[2]);
        return new EventCommand(description, startDate, endDate);
    }

    private Command parseDelete(String body) throws CloudException {
        return new DeleteCommand(stringToIndex(body));
    }

    private Command parseMark(String body) throws CloudException {
        return new MarkCommand(stringToIndex(body));
    }

    private Command parseUnmark(String body) throws CloudException {
        return new UnmarkCommand(stringToIndex(body));
    }

    private Command parseFind(String body) throws CloudException {
        if (body.isBlank()) {
            throw new UnrecognisedCommandException("Please enter a keyword");
        }
        return new FindCommand(body);
    }

    private Command parseHelp(String body) throws CloudException {
        if (body.isBlank()) {
            return new HelpCommand();
        }
        try {
            return new HelpCommand(CommandType.valueOf(body.toUpperCase()));

        } catch (IllegalArgumentException e) {
            throw new UnrecognisedCommandException();
        }
    }

    private int stringToIndex(String s) throws CloudException {
        try {
            return Integer.parseInt(s.strip());
        } catch (NumberFormatException e) {
            throw new UnrecognisedCommandException();
        }
    }
}
