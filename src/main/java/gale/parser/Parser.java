package gale.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import gale.exception.GaleException;
import gale.task.Deadline;
import gale.task.Event;
import gale.task.Priority;
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
            + "Please use: 'todo [priority] [description]'.";
        if (input.length() <= 5) {
            throw new GaleException(exceptionMsg);
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new GaleException(exceptionMsg);
        }
        Priority priority = extractPriority(description);
        description = removePriorityPrefix(description);
        return new ToDo(description, priority);
    }

    /**
     * Parses the input string into a Deadline object.
     *
     * @param input the input string to be parsed
     * @return the Deadline object parsed from the input string
     * @throws GaleException if the input string does not match the Deadline format
     */
    public static Deadline parseDeadline(String input) throws GaleException {
        String[] parts = splitInput(input, "deadline", 9, "/by", 2,
    "Oops! The wind blew away your deadline description. "
                + "Please use 'deadline [priority] [description] /by [date and time]'.");
        String description = parts[0].trim();
        String by = parts[1].trim();
        Priority priority = extractPriority(description);
        description = removePriorityPrefix(description);
        try {
            return new Deadline(description, by, priority);
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
        String[] parts = splitInput(input, "event", 6, "/from|/to", 3,
    "Oops! The wind blew away your event description. "
                + "Please use 'event [priority] [description] /from [start date] /to [end date]'.");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Priority priority = extractPriority(description);
        description = removePriorityPrefix(description);
        try {
            return new Event(description, from, to, priority);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. "
                + "Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'.");
        }
    }

    private static String[] splitInput(String input, String command, int prefixLen, String regex, int partsNum,
            String errorMsg) throws GaleException {
        if (input.length() <= prefixLen) {
            throw new GaleException(errorMsg);
        }
        if (command.equals("deadline") && !input.contains("/by")) {
            throw new GaleException(errorMsg);
        }
        if (command.equals("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new GaleException(errorMsg);
            }
            if (input.indexOf("/from") > input.indexOf("/to")) {
                throw new GaleException(errorMsg);
            }
        }
        String[] parts = input.substring(prefixLen).split(regex);
        boolean isInvalidCommand = parts.length != partsNum || parts[0].trim().isEmpty() || parts[1].trim().isEmpty();
        if (isInvalidCommand) {
            throw new GaleException(errorMsg);
        }
        return parts;
    }

    private static Priority extractPriority(String desc) {
        if (desc.startsWith("high")) {
            return Priority.HIGH;
        } else if (desc.startsWith("medium")) {
            return Priority.MEDIUM;
        } else if (desc.startsWith("low")) {
            return Priority.LOW;
        } else {
            return Priority.NONE;
        }
    }

    private static String removePriorityPrefix(String desc) {
        if (desc.startsWith("high")) {
            return desc.substring(5).trim();
        } else if (desc.startsWith("medium")) {
            return desc.substring(7).trim();
        } else if (desc.startsWith("low")) {
            return desc.substring(4).trim();
        } else {
            return desc;
        }
    }

    /**
     * Parses the input string containing an index number of a task into an integer.
     * @param input the input string to be parsed
     * @param command the type of command that the user is trying to execute, e.g. 'delete' or 'mark'
     * @return the index number of the task
     * @throws GaleException if the input string does not contain a valid index number
     */
    public static int parseIndexFromCommand(String input, String command) throws GaleException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new GaleException("Your task number got lost in the wind. "
                + "Please use '" + command + " [task number]'");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
                throw new GaleException("Oops! That task number is lost in the wind. Try again?");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new GaleException("Swoosh! The wind thinks that's not a number!");
        }
    }

    /**
     * Parses the 'find' command containing a keyword into a keyword string.
     * @param input the input string to be parsed which is a 'find' command
     * @return the keyword
     * @throws GaleException if the input string does not contain a keyword
     */
    public static String parseKeyword(String input) throws GaleException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GaleException("The wind blew away your keyword. Please use 'find [keyword]'.");
        }
        String keyword = parts[1].trim();
        return keyword;
    }
}
