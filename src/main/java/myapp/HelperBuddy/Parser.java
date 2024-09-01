package myapp.helperbuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if (command.startsWith("todo")) {
            String description = command.substring(4);
            if (!description.isEmpty()) {
                return new ToDo(description);
            }
        } else if (command.startsWith("deadline")) {
            String description = command.substring(8);
            String[] parts = command.split("/by");
            LocalDateTime deadline = parseDateTime(parts[1].trim());
            if (!description.isEmpty() && deadline != null) {
                return new Deadline(description, deadline);
            }
        } else if (command.startsWith("event")) {
            String description = command.substring(5);
            String[] parts = command.split("/from");
            String[] subParts = parts[1].split("/to");
            LocalDateTime from = parseDateTime(subParts[0].trim());
            LocalDateTime to = parseDateTime(subParts[1].trim());
            if (!description.isEmpty() && from != null && to != null) {
                return new Event(description, from, to);
            }
        }
        return null;
    }

    /**
     * Parses a command string to create an appropriate datetime object
     * If the command format is incorrect or any part of the command is invalid,
     * an error message is printed and no LocalDateTime object is returned
     *
     * @param dateTimeStr user's command string to be parsed
     * @return LocalDateTime object depending on the user's command
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
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
