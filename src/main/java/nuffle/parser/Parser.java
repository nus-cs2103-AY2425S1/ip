package nuffle.parser;

import nuffle.exception.NuffleException;
import nuffle.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String DATE_TIME_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
    // interpret user commands and create the task objects

    public static boolean validateDateTimeFormat(String dateTime) {
        Pattern pattern = Pattern.compile(DATE_TIME_FORMAT_REGEX);
        Matcher matcher = pattern.matcher(dateTime);
        return matcher.matches();
    }
    public static Task parseDeadlineCommand(String command) {
        try {
            if (!command.contains("/by")) {
                throw NuffleException.checkDeadlineFormat();
            }
            String[] desc = command.substring(8).split(" /by ");
            if (command.substring(8).trim().isEmpty()) {
                throw NuffleException.noDesc("deadline");
            }
            if (desc.length < 2 || desc[1].trim().isEmpty()) {
                throw NuffleException.checkDeadlineParams();
            }
            // check that the date input is of the correct format (yyyy-mm-dd hhmm)
            if (!validateDateTimeFormat(desc[1])) {
                throw NuffleException.checkDateTimeFormat();
            }
            String dateTime = desc[1].trim();
            // Parse the date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(dateTime, formatter);
            return new Deadline(desc[0].trim(), by);

        } catch (NuffleException e) {
            System.out.println("Nuffle caught an error > " + e.getMessage());
            return null;
        }
    }

    public static Task parseTodoCommand(String command) {
        try {
            // Program will add a To-do task to the list

            String desc = command.substring(4);
            if (desc.trim().isEmpty()) {
                throw NuffleException.noDesc("todo");
            }
            return new Todo(desc.trim());
        } catch (NuffleException e) {
            System.out.println("Nuffle caught an error > " + e.getMessage());
            return null;
        }
    }

    public static Task parseEventCommand(String command) {
        try {
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
            // check that the date input is of the correct format (yyyy-mm-dd hhmm)
            if (!validateDateTimeFormat(desc[1]) || !validateDateTimeFormat(desc[2])) {
                throw NuffleException.checkDateTimeFormat();
            }
            // Parse the date and time
            String fromDateTime = desc[1].trim();
            String toDateTime = desc[2].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(fromDateTime, formatter);
            LocalDateTime to = LocalDateTime.parse(toDateTime, formatter);
            return new Event(desc[0].trim(), from, to);
        } catch (NuffleException e) {
            System.out.println("Nuffle caught an error > " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Task> parseFindCommand(String command, TaskList inputList) {
        String desc = command.substring(4).trim();
        return inputList.findTasksByKeyword(desc);
    }

}
