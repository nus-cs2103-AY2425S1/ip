package gopher.parser;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import gopher.task.Task;
import gopher.ui.UI;

public class Parser {
    private static final DateTimeFormatter dateInputFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter dateTextFormatter
            = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

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

    public static String parseLocalDateTime(LocalDateTime date) {
        return date.format(dateInputFormatter);
    }

    public static String displayDate(LocalDateTime date) {
        return date.format(dateTextFormatter);
    }

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

    public static int parseMarkCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }

    public static int parseUnMarkCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }

    public static int parseDeleteCommand(String command) {
        String[] tokens = command.split(" ");
        String taskNumber = tokens[1];
        return Integer.parseInt(taskNumber);
    }
}
