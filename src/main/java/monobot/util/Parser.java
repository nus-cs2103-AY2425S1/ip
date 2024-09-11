package monobot.util;

import java.time.format.DateTimeParseException;

import monobot.command.AddCommand;
import monobot.command.Command;
import monobot.command.CommandType;
import monobot.command.DeleteCommand;
import monobot.command.ExitCommand;
import monobot.command.FindCommand;
import monobot.command.InvalidCommand;
import monobot.command.ListCommand;
import monobot.command.MarkCommand;
import monobot.command.UnmarkCommand;
import monobot.exception.MonoBotException;
import monobot.task.Deadline;
import monobot.task.Event;
import monobot.task.Task;
import monobot.task.Todo;

/**
 * Interprets user input and executes the appropriate actions.
 */
public class Parser {

    /**
     * Parses the user input and identifies the Command.
     *
     * @param input User's input as a string.
     * @return Command given in user's input
     * @throws MonoBotException If command in user's input is invalid or not recognised
     */
    public static Command parseCommand(String input) throws MonoBotException {
        assert input != null : "Input should not be null";

        String[] parts = input.split(" ", 2);
        String commandString = parts[0].toLowerCase();
        assert !commandString.isEmpty() : "Command string should not be empty";

        CommandType type = parseCommandType(commandString);

        switch (type) {
        case LIST:
            return new ListCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(type, parseTask(input));
        case MARK:
            return new MarkCommand(getTaskIndex(input));
        case UNMARK:
            return new UnmarkCommand(getTaskIndex(input));
        case DELETE:
            return new DeleteCommand(getTaskIndex(input));
        case BYE:
            return new ExitCommand();
        case INVALID:
            return new InvalidCommand();
        case FIND:
            String[] keywords = getSearchKeywords(input);
            assert keywords.length > 0 : "At least one search keyword should be provided";
            return new FindCommand(keywords);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses the user input and identifies the CommandType.
     *
     * @param commandString User's input as a string.
     * @return CommandType given in user's input
     */
    private static CommandType parseCommandType(String commandString) {
        assert commandString != null : "Command string should not be null";

        switch (commandString) {
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "delete":
            return CommandType.DELETE;
        case "bye":
            return CommandType.BYE;
        case "find":
            return CommandType.FIND;
        default:
            return CommandType.INVALID;
        }
    }

    /**
     * Parses the user input and identifies the Task.
     *
     * @param input User's input as a string.
     * @return Task given in user's input
     * @throws MonoBotException If Task details are missing or invalid Task type
     */
    public static Task parseTask(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        assert parts.length == 2 : "Input should have both command and details";
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Task details are missing");
        }

        CommandType command = parseCommandType(parts[0]);
        String details = parts[1].trim();

        switch (command) {
        case TODO:
            return new Todo(details);
        case DEADLINE:
            return parseDeadline(details);
        case EVENT:
            return parseEvent(details);
        default:
            throw new MonoBotException("Invalid task type");
        }
    }

    /**
     * Parses the user input and identifies the index of the Task in the list.
     *
     * @param input User's input as a string.
     * @return Index of the Task required
     * @throws MonoBotException If Task details are missing
     */
    private static int getTaskIndex(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        assert parts.length == 2 : "Input should have both command and task index";

        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Please specify which task to process");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    private static String[] getSearchKeywords(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        assert parts.length == 2 : "Input should have both command and search keywords";

        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Please specify one or more keywords to search for.");
        }
        return parts[1].trim().split("\\s+");
    }

    /**
     * Parses the user input and identifies the details of the Deadline task.
     *
     * @param details User's input as a string.
     * @return Deadline task with details as given in user's input
     * @throws MonoBotException If Deadline details are missing
     */
    private static Task parseDeadline(String details) throws MonoBotException {
        assert details != null && !details.isEmpty() : "Details for deadline task should not be empty";

        String[] deadlineDetails = details.split("/by", 2);
        if (deadlineDetails.length != 2 || deadlineDetails[1].trim().isEmpty()) {
            throw new MonoBotException("Due date/time of task is missing. "
                    + "Note that format for adding a DEADLINE task is \n"
                    + "deadline <task description> /by <due date/time>");
        }
        try {
            return new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        } catch (DateTimeParseException e) {
            throw new MonoBotException("Invalid date/time format. Please use d/M/yyyy HHmm format.");
        }
    }

    /**
     * Parses the user input and identifies the details of the Event task.
     *
     * @param details User's input as a string.
     * @return Event task with details as given in user's input
     * @throws MonoBotException If Event details are missing
     */
    private static Task parseEvent(String details) throws MonoBotException {
        assert details != null && !details.isEmpty() : "Details for event task should not be empty";

        String[] eventDetails = details.split("/from|/to ", 3);
        if (eventDetails.length != 3 || eventDetails[1].trim().isEmpty()
                || eventDetails[2].trim().isEmpty()) {
            throw new MonoBotException("Start and/or end time of event is missing. "
                    + "Note that format for adding an event is \n"
                    + "event <task description> /from <start date/time> /to <end date/time>");
        }
        try {
            return new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
        } catch (DateTimeParseException e) {
            throw new MonoBotException("Invalid date/time format"
                    + "Please use d/M/yyyy HHmm format for both start and end times.");
        }
    }
}
