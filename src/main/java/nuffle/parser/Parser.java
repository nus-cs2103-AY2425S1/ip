package nuffle.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nuffle.exception.NuffleException;
import nuffle.task.*;



/**
 * The Parser class is responsible for interpreting user commands and converting them
 * into appropriate Task objects (e.g., Todo, Deadline, Event).
 * It also validates date and time formats and handles input parsing.
 */
public class Parser {
    private static final String DATE_TIME_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    // interpret user commands and create the task objects


    /**
     * Validates whether the input date and time string follows the required format (yyyy-MM-dd HHmm).
     *
     * @param dateTime The input date and time string to validate.
     * @return True if the input follows the required format, false otherwise.
     */
    public static boolean isValidateDateTimeFormat(String dateTime) {
        Pattern pattern = Pattern.compile(DATE_TIME_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(dateTime);
        return matcher.matches();
    }

    /**
     * Parses the deadline command from the user and creates a Deadline task.
     *
     * The expected command format is: "deadline DESCRIPTION /by yyyy-MM-dd HHmm"
     *
     * @param command The user command string for creating a deadline task.
     * @return A Deadline task object, or null if there is an error in the command format.
     */
    public static Task parseDeadlineCommand(String command) throws NuffleException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";
        if (!command.contains("/by")) {
            throw NuffleException.checkDeadlineFormat();
        }
        String[] desc = command.substring(8).split(" /by ");
        assert desc.length > 0 : "description array should have at least one element.";
        if (command.substring(8).trim().isEmpty()) {
            throw NuffleException.noDesc("deadline");
        }
        if (desc.length < 2 || desc[1].trim().isEmpty()) {
            throw NuffleException.checkDeadlineParams();
        }
        assert !desc[1].equals("") : "DateTime strings for event should not be null";
        // check that the date input is of the correct format (yyyy-mm-dd hhmm)
        if (!isValidateDateTimeFormat(desc[1])) {
            throw NuffleException.checkDateTimeFormat();
        }
        String dateTime = desc[1].trim();
        // Parse the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(dateTime, formatter);
        return new Deadline(desc[0].trim(), by);
    }

    /**
     * Parses the todo command from the user and creates a Todo task.
     *
     * The expected command format is: "todo DESCRIPTION"
     *
     * @param command The user command string for creating a todo task.
     * @return A Todo task object, or null if there is an error in the command format.
     */
    public static Task parseTodoCommand(String command) throws NuffleException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty";
        // Program will add a To-do task to the list

        String desc = command.substring(4);
        if (desc.trim().isEmpty()) {
            throw NuffleException.noDesc("todo");
        }
        return new Todo(desc.trim());
    }
    /**
     * Parses the event command from the user and creates an Event task.
     *
     * The expected command format is: "event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm"
     *
     * @param command The user command string for creating an event task.
     * @return An Event task object, or null if there is an error in the command format.
     */
    public static Task parseEventCommand(String command) throws NuffleException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty";
        // Program will add an event task to the list

        if (!(command.contains("/from") || command.contains("/to"))) {
            throw NuffleException.checkEventFormat();
        }

        // Get the description of the event task first
        String[] desc = command.substring(5).split(" /from | /to ");
        if (command.substring(5).trim().isEmpty()) {
            throw NuffleException.noDesc("event");
        }
        // ensure that to / from has input
        if (desc.length < 3 || desc[1].trim().isEmpty() || desc[2].trim().isEmpty()) {
            throw NuffleException.checkEventParams();
        }
        assert !desc[1].equals("") && !desc[2].equals("") : "DateTime strings for event should not be null";
        // check that the date input is of the correct format (yyyy-mm-dd hhmm)
        if (!isValidateDateTimeFormat(desc[1]) || !isValidateDateTimeFormat(desc[2])) {
            throw NuffleException.checkDateTimeFormat();
        }
        // Parse the date and time
        String fromDateTime = desc[1].trim();
        String toDateTime = desc[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(fromDateTime, formatter);
        LocalDateTime to = LocalDateTime.parse(toDateTime, formatter);
        return new Event(desc[0].trim(), from, to);
    }

    public static ArrayList<Task> parseFindCommand(String command, TaskList inputList) {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty";
        assert inputList != null : "Task list should not be null";
        String desc = command.substring(4).trim();
        return inputList.findTasksByKeyword(desc);
    }
}
