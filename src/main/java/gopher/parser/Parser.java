package gopher.parser;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;

import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import gopher.task.Task;
import gopher.ui.UI;

/**
 * Groups the logic and functions for parsing input, command and data from
 * user and data files.
 */
public class Parser {
    /**
     * DateTimeFormater for date input
     */
    private static final DateTimeFormatter dateInputFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * DateTimeFormatter for date display
     */
    private static final DateTimeFormatter dateTextFormatter
            = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the date input into LocalDateTime Object.
     *
     * @param input date input from the user or file
     * @return LocalDateTime object of the given date
     */
    public static LocalDateTime parseDateString(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] tokens = input.split(" ");
        String date = tokens[0];
        String time = tokens.length == 2 ? tokens[1] : "";

        return LocalDateTime.parse(date
                        + " "
                        + (time.isEmpty() ? "00:00" : time)
                , dateInputFormatter);
    }

    /**
     * Parses the LocalDateTime object to date input.
     *
     * @param date LocalDateTime Object to be parsed
     * @return date string in input form(yyyy-MM-dd HH:mm)
     */
    public static String parseLocalDateTime(LocalDateTime date) {
        return date.format(dateInputFormatter);
    }

    /**
     * Displays the Date represented by LocalDateTime Object.
     *
     * @param date LocalDateTime object whose date needs to be displayed
     * @return date string in display form(MMM dd yyyy HH:mm)
     */
    public static String displayDate(LocalDateTime date) {
        return date.format(dateTextFormatter);
    }

    /**
     * Parses create task command.
     *
     * @param command string input from user or file
     * @return task of the correct type with relevant input details
     */
    public static Task parseCreateTaskCommand(String command) {
        try {
            return Task.of(command);
        } catch (UnknownCommandException e) {
            UI.printUnknownCommandWarning(e);
        } catch (DateTimeParseException e) {
            UI.printInvalidDateWarning();
        } catch (EmptyTaskDescriptionException
                 | MissingTokenException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Parses mark task as done command.
     *
     * @param command mark task command
     * @return task number of the task marked as done
     */
    public static int parseMarkCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }

    /**
     * Parses mark task as not done command.
     *
     * @param command unmark task command
     * @return task number of the task marked as not done
     */
    public static int parseUnMarkCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }

    /**
     * Parses delete task command.
     *
     * @param command delete task command
     * @return task number of the task to be deleted
     */
    public static int parseDeleteCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }

    /**
     * Parses find task command.
     *
     * @param command find task command
     * @return keyword used for searching
     */
    public static String parseFindCommand(String command) {
        String[] tokens = command.split(" ");
        StringBuilder keyword = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            keyword.append(tokens[i]);
            if (i < tokens.length - 1) {
                keyword.append(" ");
            }
        }
        return keyword.toString();
    }
}
