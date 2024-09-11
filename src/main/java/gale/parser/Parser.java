package gale.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import gale.exception.GaleException;
import gale.task.Deadline;
import gale.task.Event;
import gale.task.Task;
import gale.task.ToDo;

/**
 * Represents a parser that parses user input into tasks.
 *
 * @author kaikquah
 */
public class Parser {

    private static ArrayList<DateTimeFormatter> formatters = new ArrayList<>(
            List.of(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                    DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
    );

    /**
     * Returns the list of DateTimeFormatters used by the parser.
     *
     * @return the list of DateTimeFormatters
     */
    public static ArrayList<DateTimeFormatter> getFormatters() {
        return formatters;
    }

    /**
     * Parses the input string into a LocalDateTime object.
     *
     * @param by the input string to be parsed
     * @return the LocalDateTime object parsed from the input string
     * @throws DateTimeParseException if the input string does not match any of the DateTimeFormatters
     */
    public static LocalDateTime parseDateTime(String by) throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Unable to parse date & time", by, 0);
    }

    /**
     * Parses the input string into a Task object.
     * <p>The method calls one of the other methods parseToDo, parseDeadline, or parseEvent
     * based on the task specified in the input.</p>
     *
     * @param input the input string to be parsed
     * @return the Task object parsed from the input string
     * @throws GaleException if the input string does not match any of the task types
     */
    public static Task parseTask(String input) throws GaleException {
        if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else {
            throw new GaleException("Whoosh! The wind blew away your command. "
                + "Please use 'todo', 'deadline' or 'event'.");
        }
    }

    /**
     * Parses the input string into a ToDo object.
     *
     * @param input the input string to be parsed
     * @return the ToDo object parsed from the input string
     * @throws GaleException if the input string does not match the ToDo format
     */
    public static ToDo parseToDo(String input) throws GaleException {
        String exceptionMsg = "Oops! The wind blew away your to-do description. "
            + "Please use: 'todo [description]'.";
        if (input.length() <= 5) {
            throw new GaleException(exceptionMsg);
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new GaleException(exceptionMsg);
        }
        return new ToDo(description);
    }

    /**
     * Parses the input string into a Deadline object.
     *
     * @param input the input string to be parsed
     * @return the Deadline object parsed from the input string
     * @throws GaleException if the input string does not match the Deadline format
     */
    public static Deadline parseDeadline(String input) throws GaleException {
        String exceptionMsg = "Your deadline got tossed by the wind! "
            + "Please use 'deadline [description] /by [date]'.";
        if (input.length() <= 9) {
            throw new GaleException(exceptionMsg);
        }
        String[] strA = input.substring(9).split("/by");
        if (strA.length != 2 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()) {
            throw new GaleException(exceptionMsg);
        }
        String description = strA[0].trim();
        String by = strA[1].trim();
        try {
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. "
                + "Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'.");
        }
    }

    /**
     * Parses the input string into an Event object.
     *
     * @param input the input string to be parsed
     * @return the Event object parsed from the input string
     * @throws GaleException if the input string does not match any of the task types
     */
    public static Event parseEvent(String input) throws GaleException {
        String exceptionMsg = "Your event is lost in the wind! "
            + "Please use 'event [description] /from [start] /to [end]'.";
        if (input.length() <= 6) {
            throw new GaleException(exceptionMsg);
        }
        String[] strA = input.substring(6).split("/from|/to");
        if (strA.length != 3 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()
                || strA[2].trim().isEmpty()) {
            throw new GaleException(exceptionMsg);
        }
        String description = strA[0].trim();
        String from = strA[1].trim();
        String to = strA[2].trim();
        try {
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. "
                + "Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'.");
        }
    }
}
