package killua.util;

import killua.command.*;
import killua.task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class that provides methods for parsing commands, tasks, and dates.
 * It converts user input into command objects and task objects for the Killua application.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a command string and returns the corresponding Command object.
     *
     * @param commandStr The command string to be parsed.
     * @return The Command object corresponding to the parsed command string.
     * @throws KilluaException If the command string is invalid or unknown.
     */
    public static Command parseCommand(String commandStr) throws KilluaException {
        String[] parts = commandStr.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "todo":
            return new AddCommand(parseTodo(arguments));
        case "deadline":
            return new AddCommand(parseDeadline(arguments));
        case "event":
            return new AddCommand(parseEvent(arguments));
        case "delete":
            return new DeleteCommand(parseIndex(arguments));
        case "mark":
            return new MarkCommand(parseIndex(arguments));
        case "unmark":
            return new UnmarkCommand(parseIndex(arguments));
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "on":
            if (arguments.isEmpty()) {
                throw new KilluaException("Arguments cannot be empty!");
            }
            return new OnCommand(arguments);
        case "find":
            if (arguments.isEmpty()) {
                throw new KilluaException("Arguments cannot be empty!");
            }
            return new FindCommand(arguments);
        default:
            throw new KilluaException("Unknown command: " + commandWord);
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
        if (arguments.isEmpty()) {
            throw new KilluaException("Arguments cannot be empty!");
        }
        return new Todo(arguments);
    }

    /**
     * Parses a "deadline" command argument and returns a Deadline object.
     *
     * @param arguments The arguments of the "deadline" command.
     * @return The Deadline object created from the arguments.
     * @throws KilluaException If the arguments are empty or in an incorrect format.
     */
    public static Deadline parseDeadline(String arguments) throws KilluaException {
        if (arguments.isEmpty()) {
            throw new KilluaException("Arguments cannot be empty!");
        }

        try {
            String[] parts = arguments.split(" /by ");
            String description = parts[0].strip();
            String dateTimeString = parts[1].strip();

            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
                return new Deadline(description, dateTime);
            } catch (DateTimeParseException e) {
                LocalDate date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
                return new Deadline(description, date);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException("Please use the correct format for deadlines: deadline <description> /by <yyyy-mm-dd> OR deadline <description> /by <yyyy-mm-dd hh:mm>");
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
        if (arguments.isEmpty()) {
            throw new KilluaException("Arguments cannot be empty!");
        }

        try {
            String[] parts = arguments.split(" /from ");
            String description = parts[0].strip();
            String[] dateStrings = parts[1].split(" /to ");

            String fromDateTimeString = dateStrings[0].strip();
            String toDateTimeString = dateStrings[1].strip();

            try {
                LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, DATE_TIME_FORMATTER);
                LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, DATE_TIME_FORMATTER);
                return new Event(description, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                LocalDate fromDate = LocalDate.parse(fromDateTimeString, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(toDateTimeString, DATE_FORMATTER);
                return new Event(description, fromDate, toDate);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException("Please use the correct format for events: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> OR event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>");
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
        if (arguments.isEmpty()) {
            throw new KilluaException("Arguments cannot be empty!");
        }

        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new KilluaException("Please provide a valid task number!");
        }  catch (IndexOutOfBoundsException e1) {
            throw new KilluaException("Task " + arguments + " not found!");
        }
    }

    /**
     * Parses a task string from the storage format and returns the corresponding Task object.
     *
     * @param line The task string from the storage.
     * @return The Task object parsed from the string.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public static Task parseTask(String line) {
        char taskType = line.charAt(0);
        boolean isDone = line.charAt(4) == '1';
        String argument = line.substring(8);

        Task task;

        if (taskType == 'T') {
            task = new Todo(argument);
        } else if (taskType == 'D') {
            String[] strs = argument.split("\\|", 2);
            task = getDeadline(strs[0].strip(), strs[1].strip());
        } else if (taskType == 'E') {
            String[] strs = argument.split("\\|", 3);
            task = getEvent(strs[0].strip(), strs[1].strip(), strs[2].strip());
        } else {
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
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
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private static Task getDeadline(String description, String dateTimeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
            return new Deadline(description, dateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, DATE_FORMATTER);
                return new Deadline(description, date);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: " + dateTimeString);
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
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private static Task getEvent(String description, String dateTimeStringFrom, String dateTimeStringTo) {
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(dateTimeStringFrom, DATE_TIME_FORMATTER);
            LocalDateTime toDateTime = LocalDateTime.parse(dateTimeStringTo, DATE_TIME_FORMATTER);
            return new Event(description, fromDateTime, toDateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate fromDate = LocalDate.parse(dateTimeStringFrom, DATE_FORMATTER);
                LocalDate toDate = LocalDate.parse(dateTimeStringTo, DATE_FORMATTER);
                return new Event(description, fromDate, toDate);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: from '" + dateTimeStringFrom + "' to '" + dateTimeStringTo + "'");
            }
        }
    }
}
