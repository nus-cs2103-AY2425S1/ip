package monobot.util;

import monobot.command.*;
import monobot.exception.MonoBotException;
import monobot.task.Deadline;
import monobot.task.Event;
import monobot.task.Task;
import monobot.task.Todo;

import java.time.format.DateTimeParseException;

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
        String[] parts = input.split(" ", 2);
        String commandString = parts[0].toLowerCase();
        CommandType type = parseCommandType(commandString);

        return switch (type) {
            case LIST -> new ListCommand();
            case TODO, DEADLINE, EVENT -> new AddCommand(type, parseTask(input));
            case MARK -> new MarkCommand(getTaskIndex(input));
            case UNMARK -> new UnmarkCommand(getTaskIndex(input));
            case DELETE -> new DeleteCommand(getTaskIndex(input));
            case BYE -> new ExitCommand();
            case INVALID -> new InvalidCommand();
        };
    }

    /**
     * Parses the user input and identifies the CommandType.
     *
     * @param commandString User's input as a string.
     * @return CommandType given in user's input
     */
    private static CommandType parseCommandType(String commandString) {
        return switch (commandString) {
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "delete" -> CommandType.DELETE;
            case "bye" -> CommandType.BYE;
            default -> CommandType.INVALID;
        };
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
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("monobot.task.Task details are missing");
        }

        CommandType command = parseCommandType(parts[0]);
        String details = parts[1].trim();

        return switch (command) {
            case TODO -> new Todo(details);
            case DEADLINE -> parseDeadline(details);
            case EVENT -> parseEvent(details);
            default -> throw new MonoBotException("Invalid task type");
        };
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
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new MonoBotException("Please specify which task to process");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    /**
     * Parses the user input and identifies the details of the Deadline task.
     *
     * @param details User's input as a string.
     * @return Deadline task with details as given in user's input
     * @throws MonoBotException If Deadline details are missing
     */
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

    /**
     * Parses the user input and identifies the details of the Event task.
     *
     * @param details User's input as a string.
     * @return Event task with details as given in user's input
     * @throws MonoBotException If Event details are missing
     */
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
