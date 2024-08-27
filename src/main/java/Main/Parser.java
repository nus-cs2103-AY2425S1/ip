package Main;

import Commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                return parseTodoCommand(parts);
            case "deadline":
                return parseDeadlineCommand(parts);
            case "event":
                return parseEventCommand(parts);
            case "delete":
                return parseDeleteCommand(parts);
            case "mark":
                return parseMarkCommand(parts);
            case "unmark":
                return parseUnmarkCommand(parts);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand("Unknown command: " + commandWord);
        }
    }

    private static Command parseTodoCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("todo WHAT????");
        }
        return new ToDoCommand(parts[1].trim());
    }

    private static Command parseDeadlineCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("WHEN IS THE DEADLINE???\nPlease use yyyy-MM-dd HHmm format.");
        }
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            return new InvalidCommand("WHEN IS THE DEADLINE???\nPlease use yyyy-MM-dd HHmm format.");
        }
        String taskDescription = deadlineParts[0].trim();
        try {
            LocalDateTime dueWhen = parseDateTime(deadlineParts[1].trim());
            return new DeadlineCommand(taskDescription, dueWhen);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OI ENTER YOUR DATE AND TIME PROPERLY!\nPlease use yyyy-MM-dd HHmm format.");
        }
    }

    private static Command parseEventCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("WHEN DOES IT START???\nPlease use yyyy-MM-dd HHmm format.");
        }
        String[] eventParts = parts[1].split("/from", 2);
        if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
            return new InvalidCommand("WHEN DOES IT START???\nPlease use yyyy-MM-dd HHmm format.");
        }
        String[] eventTimes = eventParts[1].split("/to", 2);
        if (eventTimes.length < 2 || eventTimes[1].trim().isEmpty()) {
            return new InvalidCommand("WHEN DOES IT END???\nPlease use yyyy-MM-dd HHmm format.");
        }
        String eventDescription = eventParts[0].trim();
        try {
            LocalDateTime startWhen = parseDateTime(eventTimes[0].trim());
            LocalDateTime endWhen = parseDateTime(eventTimes[1].trim());
            return new EventCommand(eventDescription, startWhen, endWhen);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("OI ENTER YOUR DATE AND TIME PROPERLY!\nPlease use yyyy-MM-dd HHmm format.");
        }
    }

    private static Command parseDeleteCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("delete WHAT???");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1].trim());
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new InvalidCommand("THIS ONE NOT INTEGER!!!");
        }
    }

    private static Command parseMarkCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("mark WHAT???");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1].trim());
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new InvalidCommand("THIS ONE NOT INTEGER!!!");
        }
    }

    private static Command parseUnmarkCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("unmark WHAT???");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1].trim());
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new InvalidCommand("THIS ONE NOT INTEGER!!!");
        }
    }

    // Method to parse date-time strings into LocalDateTime objects
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}

