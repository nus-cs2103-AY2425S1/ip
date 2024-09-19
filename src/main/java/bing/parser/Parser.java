package bing.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import bing.command.*;
import java.util.Arrays;

public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Command parse(String input) {
        String[] tokens = input.split(" ", 2); // Split into command and rest
        String commandType = tokens[0];
        String details = tokens.length > 1 ? tokens[1] : "";

        switch (commandType) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    int markIndex = Integer.parseInt(details) - 1;
                    return new MarkCommand(markIndex);
                } catch (NumberFormatException e) {
                    return new InvalidCommand();
                }
            case "unmark":
                try {
                    int unmarkIndex = Integer.parseInt(details) - 1;
                    return new UnmarkCommand(unmarkIndex);
                } catch (NumberFormatException e) {
                    return new InvalidCommand();
                }
            case "delete":
                try {
                    int deleteIndex = Integer.parseInt(details) - 1;
                    return new DeleteCommand(deleteIndex);
                } catch (NumberFormatException e) {
                    return new InvalidCommand();
                }
            case "todo":
                return new ToDoCommand(details.trim()); // Extract task info
            case "deadline":
                return parseDeadline(details);
            case "event":
                return parseEvent(details);
            case "find":
                return new FindCommand(details.trim());
            default:
                return new InvalidCommand();
        }
    }


    private Command parseDeadline(String details) {
        String[] parts = details.split(" /by ");
        if (parts.length < 2) {
            return new InvalidCommand();
        }
        String taskInfo = parts[0].trim();
        String dateStr = parts[1].trim();

        try {
            LocalDateTime deadline = LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
            return new DeadlineCommand(taskInfo, deadline);
        } catch (DateTimeParseException e) {
            return new InvalidCommand();
        }
    }

    private Command parseEvent(String details) {
        String[] parts = details.split(" /from ");
        if (parts.length < 2) {
            return new InvalidCommand();
        }
        String taskInfo = parts[0].trim();
        String[] dateParts = parts[1].split(" /to ");
        if (dateParts.length < 2) {
            return new InvalidCommand();
        }
        String fromStr = dateParts[0].trim();
        String toStr = dateParts[1].trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toStr, DATE_TIME_FORMATTER);
            return new EventCommand(taskInfo, from, to);
        } catch (DateTimeParseException e) {
            return new InvalidCommand();
        }
    }
}
