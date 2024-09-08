package myapp.helperbuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class which helps to create a new Task object
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses a command string to create an appropriate Task object
     * If the command format is incorrect or any part of the command is invalid,
     * an error message is printed and no Task object is returned
     *
     * @param command user's command string to be parsed
     * @return Task object depending on the user's command
     */
    public static Task parseCommand(String command) {
        assert command != null : "Command string should not be null.";
        command = command.trim();
        assert !command.isEmpty() : "Command string should not be empty.";

        if (command.startsWith("todo")) {
            String description = command.substring(4);
            assert !description.isEmpty() : "ToDo description should not be empty.";
            return new ToDo(description);
        } else if (command.startsWith("deadline")) {
            String description = command.substring(8).trim();
            String[] parts = command.split("/by", 2);
            assert parts.length == 2 : "Deadline command should contain '/by' separator.";
            LocalDateTime deadline = parseDateTime(parts[1].trim());
            assert !description.isEmpty() : "Deadline description should not be empty.";
            assert deadline != null : "Deadline date/time should be valid.";
            return new Deadline(description, deadline);
        } else if (command.startsWith("event")) {
            String description = command.substring(5).trim();
            String[] parts = command.split("/from", 2);
            assert parts.length == 2 : "Event command should contain '/from' separator.";
            String[] subParts = parts[1].split("/to", 2);
            assert subParts.length == 2 : "Event command should contain '/to' separator.";
            LocalDateTime from = parseDateTime(subParts[0].trim());
            LocalDateTime to = parseDateTime(subParts[1].trim());
            assert !description.isEmpty() : "Event description should not be empty.";
            assert from != null : "Event start date/time should be valid.";
            assert to != null : "Event end date/time should be valid.";
            return new Event(description, from, to);
        }
        return null;
    }

    /**
     * Parses a command string to create an appropriate datetime object
     * If the command format is incorrect or any part of the command is invalid,
     * an error message is printed and no LocalDateTime object is returned
     * @param dateTimeStr user's command string to be parsed
     * @return LocalDateTime object depending on the user's command
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        assert dateTimeStr != null : "DateTime string should not be null.";
        dateTimeStr = dateTimeStr.trim();
        if (dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
