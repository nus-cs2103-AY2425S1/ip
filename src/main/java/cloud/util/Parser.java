package cloud.util;

import cloud.command.Command;
import cloud.command.DeadlineCommand;
import cloud.command.DeleteCommand;
import cloud.command.EventCommand;
import cloud.command.FindCommand;
import cloud.command.ListCommand;
import cloud.command.MarkCommand;
import cloud.command.TodoCommand;
import cloud.command.UnmarkCommand;
import cloud.exception.CloudException;
import cloud.exception.UnrecognisedCommandException;

public class Parser {

    public Command parse(String input) throws CloudException {
        String[] split = input.split(" ", 2);
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
        if (body.strip().length() > 0) {
            throw new CloudException("Invalid ");
        }
        return new ListCommand();
    }

    private Command parseTodo(String body) throws CloudException {
        if (body.strip().length() == 0) {
            throw new UnrecognisedCommandException("Add a description to you task and try again");
        }
        return new TodoCommand(body);
    }

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
        if (body.strip().length() == 0) {
            throw new UnrecognisedCommandException("Please enter a keyword");
        }
        return new FindCommand(body);
    }

    private int stringToIndex(String s) throws CloudException {
        int index;
        try {
            index = Integer.parseInt(s.strip());
        } catch (NumberFormatException e) {
            throw new UnrecognisedCommandException();
        }
        return index;
    }
}
