package joe.utils;

import joe.commands.AddTaskCommand;
import joe.commands.Command;
import joe.commands.DeleteCommand;
import joe.commands.FindCommand;
import joe.commands.MarkCommand;
import joe.commands.QueryScheduleCommand;
import joe.commands.UnmarkCommand;
import joe.exceptions.CorruptedFileException;
import joe.exceptions.InvalidCommandException;
import joe.exceptions.InvalidIndexException;
import joe.tasks.Deadline;
import joe.tasks.Event;
import joe.tasks.Task;
import joe.tasks.TaskList;
import joe.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents a parser to parse user input and file input.
 */
public class Parser {
    private static final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM/d/yyyy HH:mm");
    private static final String DELIMITER = " \\| ";
    private final TaskList tasks;

    /**
     * Constructs a Parser object.
     * @param tasks the task list
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * Formats a LocalDateTime object to a String.
     *
     * @param dateTime the LocalDateTime object to be formatted
     * @return a String representing the formatted LocalDateTime object
     */
    public static String formatDateTime(LocalDateTime dateTime)
            throws DateTimeParseException {
        return dateTime.format(PARSE_FORMATTER);
    }
    /**
     * Creates a LocalDateTime object with an arbitrary time.
     *
     * @param date the date to be formatted
     * @return a LocalDateTime object with an arbitrary time
     */
    public static LocalDateTime createLocalDateTimeWithArbitraryTime(String date)
            throws DateTimeParseException {
        return LocalDateTime.parse(date.strip() + " 1200", PARSE_FORMATTER);
    }

    /**
     * Parses a date and time string to a LocalDateTime object.
     * @param dateTime the date and time string to be parsed
     * @return a LocalDateTime object representing the date and time string
     */
    public static LocalDateTime parseDateTimeString(String dateTime)
            throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, PARSE_FORMATTER);
    }

    /**
     * Prints a LocalDateTime object to a formatted string.
     * @param dateTime the LocalDateTime object to be printed
     * @return a formatted string representing the LocalDateTime object
     */
    public static String printDateTime(LocalDateTime dateTime) {
        return dateTime.format(PRINT_FORMATTER);
    }

    /**
     * Parses the user input and returns the respective Command object.
     *
     * @param userCmd the String representation of the user input
     * @return a Command object representing the user input
     * @throws IllegalArgumentException if the user input is invalid
     * @throws InvalidCommandException if the user input is not recognised
     * @throws DateTimeParseException if the date and time string is not in the correct format
     */
    public Command parseCommand(String userCmd)
            throws IllegalArgumentException, InvalidCommandException, DateTimeParseException {
        Command c;
        if (userCmd.startsWith("mark")) {
            c = new MarkCommand(tasks, getTaskIndex(userCmd));
        } else if (userCmd.startsWith("unmark")) {
            c = new UnmarkCommand(tasks, getTaskIndex(userCmd));
        } else if (userCmd.startsWith("delete")) {
            c = new DeleteCommand(tasks, getTaskIndex(userCmd));
        } else if (userCmd.startsWith("todo")) {
            c = parseToDo(userCmd);
        } else if (userCmd.startsWith("deadline")) {
            c = parseDeadline(userCmd);
        } else if (userCmd.startsWith("event")) {
            c = parseEvent(userCmd);
        } else if (userCmd.startsWith("query")) {
            c = new QueryScheduleCommand(tasks, userCmd.substring(6));
        } else if (userCmd.startsWith("find")) {
            c = new FindCommand(tasks, userCmd.substring(5));
        } else {
            throw new InvalidCommandException(userCmd);
        }

        return c;
    }

    private AddTaskCommand parseToDo(String userCmd) {
        return new AddTaskCommand(tasks, new ToDo(userCmd.substring(4)));
    }

    /**
     * Parses the user input and returns the respective Command object for an Event task.
     * @param userCmd the String representation of the user input
     * @return a Command object representing the user input
     * @throws IllegalArgumentException if the user input is invalid
     * @throws DateTimeParseException if the date and time string is not in the correct format
     */
    private Command parseEvent(String userCmd)
            throws IllegalArgumentException, DateTimeParseException {
        Command c;
        String[] params = userCmd.substring(5).split(DELIMITER);

        if (params.length < 2) {
            throw new IllegalArgumentException("Event: Did not provide start and end date/time");
        } else if (params.length < 3) {
            throw new IllegalArgumentException("Event: Did not provide end date/time");
        }

        if (!params[1].startsWith("from")) {
            throw new InvalidCommandException(userCmd);
        }

        if (!params[2].startsWith("to")) {
            throw new InvalidCommandException(userCmd);
        }

        LocalDateTime start = parseDateTimeString(
                params[1].substring(5).strip()
        );
        LocalDateTime end = parseDateTimeString(
                params[2].substring(3).strip()
        );

        c = new AddTaskCommand(tasks, new Event(params[0], start, end));
        return c;
    }

    /**
     * Parses the user input and returns the respective Command object for a Deadline task.
     * @param userCmd the String representation of the user input
     * @return a Command object representing the user input
     * @throws IllegalArgumentException if the user input is invalid
     * @throws DateTimeParseException if the date and time string is not in the correct format
     */
    private Command parseDeadline(String userCmd)
            throws DateTimeParseException, IllegalArgumentException {
        Command c;
        String[] params = userCmd.substring(8).split(DELIMITER);

        if (params.length < 2) {
            throw new IllegalArgumentException("Deadline: You did not provide a due date/time.");
        }

        if (!params[1].startsWith("by")) {
            throw new InvalidCommandException(userCmd);
        }

        LocalDateTime due = parseDateTimeString(params[1].substring(3).strip());

        c = new AddTaskCommand(tasks, new Deadline(params[0], due));
        return c;
    }

    /**
     * Extracts the first contiguous numerical substring from a string.
     *
     * @param userCmd the String representation of the user input
     * @return the numerical substring as an integer
     */
    public int getTaskIndex(String userCmd) throws InvalidIndexException {
        int idx = 0;
        int n = userCmd.length();

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(userCmd.charAt(i))) {
                idx = parseTaskIndex(userCmd, i, n);
                break;
            }

            // For the case where no number was provided
            if (i == n - 1) {
                throw new InvalidCommandException(userCmd);
            }
        }

        if (idx <= 0) {
            throw new InvalidIndexException(idx);
        }

        return idx;
    }

    private static int parseTaskIndex(String userCmd, int i, int n) {
        int idx;
        int endIdx = i + 1;
        while (endIdx < n && Character.isDigit(userCmd.charAt(endIdx))) {
            endIdx++;
        }
        idx = Integer.parseInt(userCmd.substring(i, endIdx));
        return idx;
    }
    /**
     * Parses the line from the joe.txt file and returns the respective Task object.
     *
     * @param line the line read from the joe.txt file
     * @return a Task object representing the line read
     * @throws CorruptedFileException if the file is corrupted
     */
    public static Task parseTask(String line) throws CorruptedFileException {
        String[] params = line.split(DELIMITER);
        Task t;
        String type = params[0];

        switch (type) {
        case "T":
            t = new ToDo(params[2]);
            break;
        case "D":
            LocalDateTime due = parseDateTimeString(params[3].substring(3).strip());
            t = new Deadline(params[2], due);
            break;
        case "E":
            LocalDateTime start = parseDateTimeString(params[3].substring(5).strip());
            LocalDateTime end = parseDateTimeString(params[4].substring(3).strip());
            t = new Event(params[2], start, end);
            break;
        default:
            throw new CorruptedFileException();
        }

        boolean isDone = params[1].equals("1");
        t.setDone(isDone);
        return t;
    }
}

