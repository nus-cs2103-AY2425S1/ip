package main;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting it
 * into specific command objects that can be executed by the application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param userInput the user input to parse
     * @return the command corresponding to the user input
     */
    public static Command parseCommand(String userInput) {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "todo":
            return parseTodoCommand(parts);
            // Fallthrough
        case "deadline":
            return parseDeadlineCommand(parts);
            // Fallthrough
        case "event":
            return parseEventCommand(parts);
            // Fallthrough
        case "delete":
            return parseDeleteCommand(parts);
            // Fallthrough
        case "mark":
            return parseMarkCommand(parts);
            // Fallthrough
        case "unmark":
            return parseUnmarkCommand(parts);
            // Fallthrough
        case "list":
            return new ListCommand();
            // Fallthrough
        case "bye":
            return new ExitCommand();
            // Fallthrough
        case "find":
            return parseFindCommand(parts);
            // Fallthrough
        default:
            return new InvalidCommand("Unknown command: " + commandWord);
            // Fallthrough
        }
    }

    /**
     * Parses a to-do command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code ToDoCommand} or {@code InvalidCommand}
     */
    private static Command parseTodoCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("todo WHAT????");
        }
        return new ToDoCommand(parts[1].trim());
    }

    /**
     * Parses a deadline command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code DeadlineCommand} or {@code InvalidCommand}
     */
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

    /**
     * Parses an event command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code EventCommand} or {@code InvalidCommand}
     */
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

    /**
     * Parses a delete command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code DeleteCommand} or {@code InvalidCommand}
     */
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

    /**
     * Parses a mark command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code MarkCommand} or {@code InvalidCommand}
     */
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

    /**
     * Parses an unmark command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code UnmarkCommand} or {@code InvalidCommand}
     */
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

    /**
     * Parses a find command from user input.
     *
     * @param parts the split user input
     * @return the corresponding {@code FindCommand} or {@code InvalidCommand}
     */
    private static Command parseFindCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new InvalidCommand("find WHAT????");
        }
        return new FindCommand(parts[1].trim());
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     * Expected format is "yyyy-MM-dd HHmm".
     *
     * @param dateTime the date-time string to parse
     * @return the parsed {@code LocalDateTime} object
     * @throws DateTimeParseException if the date-time string is not in the expected format
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}

