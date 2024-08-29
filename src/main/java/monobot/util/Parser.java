package monobot.util;

import monobot.command.*;
import monobot.exception.MonoBotException;
import monobot.task.Deadline;
import monobot.task.Event;
import monobot.task.Task;
import monobot.task.Todo;

import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseCommand(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        String commandString = parts[0].toLowerCase();
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
        default:
            throw new IllegalArgumentException();
        }
    }

    private static CommandType parseCommandType(String commandString) {
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
        default:
            return CommandType.INVALID;
        }
    }

    public static Task parseTask(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("monobot.task.Task details are missing");
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

    private static int getTaskIndex(String input) throws MonoBotException {
        String[] parts = input.split(" ", 2);
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Please specify which task to process");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    private static Task parseDeadline(String details) throws MonoBotException {
        String[] deadlineDetails = details.split("/by", 2);
        if (deadlineDetails.length != 2 || deadlineDetails[1].trim().isEmpty()) {
            throw new MonoBotException("Due date/time of task is missing. " +
                    "Note that format for adding a DEADLINE task is \n" +
                    "deadline <task description> /by <due date/time>");
        }
        try {
            return new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        } catch (DateTimeParseException e) {
            throw new MonoBotException("Invalid date/time format. Please use d/M/yyyy HHmm format.");
        }
    }

    private static Task parseEvent(String details) throws MonoBotException {
        String[] eventDetails = details.split("/from|/to ", 3);
        if (eventDetails.length != 3 || eventDetails[1].trim().isEmpty()
                || eventDetails[2].trim().isEmpty()) {
            throw new MonoBotException("Start and/or end time of event is missing. " +
                    "Note that format for adding an event is \n" +
                    "event <task description> /from <start date/time> /to <end date/time>");
        }
        try {
            return new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
        } catch (DateTimeParseException e) {
            throw new MonoBotException("Invalid date/time format. Please use d/M/yyyy HHmm format for both start and end times.");
        }
    }
}
