package killua.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import killua.command.AddCommand;
import killua.command.Command;
import killua.command.DeleteCommand;
import killua.command.ExitCommand;
import killua.command.FindCommand;
import killua.command.ListCommand;
import killua.command.MarkCommand;
import killua.command.OnCommand;
import killua.command.UnmarkCommand;
import killua.task.Deadline;
import killua.task.Event;
import killua.task.Task;
import killua.task.Todo;
import killua.util.KilluaException;


/**
 * Utility class that provides methods for parsing commands, tasks, and dates.
 * It converts user input into command objects and task objects for the Killua application.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_FROM_HISTORY = DateTimeFormatter
            .ofPattern("MMM d yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER_FROM_HISTORY = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final String ARGUMENT_EMPTY_MESSAGE = "Wow, you just forgot the arguments. Good job!";
    private static final String INVALID_COMMAND_MESSAGE = "Seriously? I have no clue what you're trying to do.";
    private static final String INVALID_DEADLINE_MESSAGE = "Wrong format again? Try using this if you can: \n"
            + "\tdeadline/ddl <description> /by <yyyy-mm-dd[ hh:mm]>";
    private static final String INVALID_EVENT_MESSAGE = "Can't even get the event format right? It's not that hard:\n"
            + "\tevent/eve <description> /from <yyyy-mm-dd[ hh:mm]> /to <yyyy-mm-dd[ hh:mm]>";
    private static final String INVALID_NUMBER_MESSAGE = "Are you sure that's a number? Because it's definitely not!";
    private static final String INVALID_DATE_FORMAT_FROM_HISTORY = "Couldn't even handle the date from history."
            + " Here's what went wrong: ";

    /**
     * Parses a command string and returns the corresponding Command object.
     *
     * @param commandStr The command string to be parsed.
     * @return The Command object corresponding to the parsed command string.
     * @throws KilluaException If the command string is invalid or unknown.
     */
    public static Command parseCommand(String commandStr) throws KilluaException {
        // split user input into 2 parts: command and argument
        String[] parts = commandStr.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "todo":
            return new AddCommand(parseTodo(arguments));
        case "deadline", "ddl":
            return new AddCommand(parseDeadline(arguments));
        case "event", "eve":
            return new AddCommand(parseEvent(arguments));
        case "delete", "del":
            return new DeleteCommand(parseIndex(arguments));
        case "mark", "m":
            return new MarkCommand(parseIndex(arguments));
        case "unmark", "um":
            return new UnmarkCommand(parseIndex(arguments));
        case "list", "ls":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "on":
            checkEmptyArgument(arguments);
            return new OnCommand(arguments);
        case "find":
            checkEmptyArgument(arguments);
            return new FindCommand(arguments);
        default:
            throw new KilluaException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Parses a "todo" command argument and returns a Todo object.
     *
     * @param arguments The arguments of the "todo" command.
     * @return The Todo object created from the arguments.
     * @throws KilluaException If the arguments are empty.
     */
    public static Todo parseTodo(String arguments) throws KilluaException {
        checkEmptyArgument(arguments);
        return new Todo(arguments);
    }

    /**
     * Raise an exception if a command has no argument.
     *
     * @param arguments The arguments of a command.
     * @throws KilluaException If the arguments are empty.
     */
    private static void checkEmptyArgument(String arguments) throws KilluaException {
        if (arguments.trim().isEmpty()) {
            throw new KilluaException(ARGUMENT_EMPTY_MESSAGE);
        }
    }

    /**
     * Parses a "deadline" command argument and returns a Deadline object.
     *
     * @param arguments The arguments of the "deadline" command.
     * @return The Deadline object created from the arguments.
     * @throws KilluaException If the arguments are empty or in an incorrect format.
     */
    public static Deadline parseDeadline(String arguments) throws KilluaException {
        checkEmptyArgument(arguments);

        try {
            String[] parts = arguments.split(" /by ", 2);
            String description = parts[0].trim();
            String dateTimeString = parts[1].trim();
            try {
                // try parsing yyyy-MM-dd HH:mm
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
                return new Deadline(description, dateTime);
            } catch (DateTimeParseException e) {
                // try parsing yyyy-MM-dd
                LocalDate date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
                return new Deadline(description, date);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException(INVALID_DEADLINE_MESSAGE);
        }
    }

    /**
     * Parses an "event" command argument and returns an Event object.
     *
     * @param arguments The arguments of the "event" command.
     * @return The Event object created from the arguments.
     * @throws KilluaException If the arguments are empty or in an incorrect format.
     */
    public static Event parseEvent(String arguments) throws KilluaException {
        checkEmptyArgument(arguments);

        try {
            String[] parts = arguments.split(" /from ");
            String description = parts[0].strip();
            String[] dateStrings = parts[1].split(" /to ");

            String fromDateTimeString = dateStrings[0].strip();
            String toDateTimeString = dateStrings[1].strip();
            try {
                // try parsing yyyy-MM-dd HH:mm
                LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, DATE_TIME_FORMATTER);
                LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, DATE_TIME_FORMATTER);
                return new Event(description, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                // try parsing yyyy-MM-dd
                LocalDate fromDate = LocalDate.parse(fromDateTimeString, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(toDateTimeString, DATE_FORMATTER);
                return new Event(description, fromDate, toDate);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException(INVALID_EVENT_MESSAGE);
        }
    }

    /**
     * Parses an index argument and returns the corresponding integer index.
     *
     * @param arguments The index argument.
     * @return The integer index parsed from the arguments.
     * @throws KilluaException If the arguments are empty or not a valid integer.
     */
    public static int parseIndex(String arguments) throws KilluaException {
        checkEmptyArgument(arguments);

        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new KilluaException(INVALID_NUMBER_MESSAGE);
        }
    }

    /**
     * Parses a task string from the storage format and returns the corresponding Task object.
     *
     * @param line The task string from the storage.
     * @return The Task object parsed from the string.
     * @throws KilluaException If date(time) format from history is incorrect.
     */
    public static Task parseTaskFromHistory(String line) throws KilluaException {
        int taskTypeIndex = 0;
        int taskIsDoneIndex = 4;
        int argumentStartIndex = 8;
        char taskType = line.charAt(taskTypeIndex);
        boolean isDone = line.charAt(taskIsDoneIndex) == '1';
        String argument = line.substring(argumentStartIndex);

        Task task;

        switch (taskType) {
        case 'T':
            task = new Todo(argument);
            break;
        case 'D':
            String[] deadlineParts = argument.split("\\|", 2);
            task = getDeadlineFromHistory(deadlineParts[0].strip(), deadlineParts[1].strip());
            break;
        case 'E':
            String[] eventParts = argument.split("\\|", 3);
            task = getEventFromHistory(eventParts[0].strip(), eventParts[1].strip(), eventParts[2].strip());
            break;
        default:
            task = null;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates a Deadline task from the description and date string.
     *
     * @param description The description of the deadline.
     * @param dateTimeString The date string for the deadline.
     * @return The Deadline task created from the given description and date string.
     * @throws KilluaException If the date format is invalid.
     */
    private static Task getDeadlineFromHistory(String description, String dateTimeString) throws KilluaException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER_FROM_HISTORY);
            return new Deadline(description, dateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, DATE_FORMATTER_FROM_HISTORY);
                return new Deadline(description, date);
            } catch (DateTimeParseException e2) {
                throw new KilluaException(INVALID_DATE_FORMAT_FROM_HISTORY + dateTimeString);
            }
        }
    }

    /**
     * Creates an Event task from the description and date strings.
     *
     * @param description The description of the event.
     * @param dateTimeStringFrom The start date string of the event.
     * @param dateTimeStringTo The end date string of the event.
     * @return The Event task created from the given description and date strings.
     * @throws KilluaException If the date format is invalid.
     */
    private static Task getEventFromHistory(String description,
                                            String dateTimeStringFrom,
                                            String dateTimeStringTo) throws KilluaException {
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(dateTimeStringFrom, DATE_TIME_FORMATTER_FROM_HISTORY);
            LocalDateTime toDateTime = LocalDateTime.parse(dateTimeStringTo, DATE_TIME_FORMATTER_FROM_HISTORY);
            return new Event(description, fromDateTime, toDateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate fromDate = LocalDate.parse(dateTimeStringFrom, DATE_FORMATTER_FROM_HISTORY);
                LocalDate toDate = LocalDate.parse(dateTimeStringTo, DATE_FORMATTER_FROM_HISTORY);
                return new Event(description, fromDate, toDate);
            } catch (DateTimeParseException e2) {
                throw new KilluaException(INVALID_DATE_FORMAT_FROM_HISTORY + "from '"
                        + dateTimeStringFrom + "' to '" + dateTimeStringTo + "'");
            }
        }
    }
}
