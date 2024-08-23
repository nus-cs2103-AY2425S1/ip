package myapp.core;

import myapp.command.*;
import myapp.utils.CommandType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String input) throws BingBongException {
        CommandType command = CommandType.fromString(input);
        String description;

        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST_ON:
            String date = input.substring(8).trim();  // Extract the date after "list on "
            return new ListOnCommand(date);
        case LIST:
            return new ListCommand();
        case MARK:
            int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(markIndex);
        case UNMARK:
            int unMarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnMarkCommand(unMarkIndex);
        case DELETE:
            int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(deleteIndex);
        case TODO:
            description = Parser.parseDescription(input, CommandType.TODO);
            if (description.isEmpty()) {
                throw new BingBongException("The description of a todo cannot be empty.");
            }
            return new ToDoCommand(description);
        case DEADLINE:
            description = Parser.parseDescription(input, CommandType.DEADLINE);
            LocalDateTime byDateTime = Parser.parseDeadlineDateTime(input);
            if (description.isEmpty()) {
                throw new BingBongException("The description of a deadline cannot be empty.");
            }
            return new DeadlineCommand(description, byDateTime);
        case EVENT:
            description = Parser.parseDescription(input, CommandType.EVENT);
            LocalDateTime[] dateTimes = Parser.parseEventDateTime(input);
            if (description.isEmpty()) {
                throw new BingBongException("The description of an event cannot be empty.");
            }
            return new EventCommand(description, dateTimes[0], dateTimes[1]);
        case INVALID:
        default:
            throw new BingBongException("Command not recognized. Please try again...");
        }
    }

    public static String parseDescription(String input, CommandType type) throws BingBongException {
        switch (type) {
        case TODO:
            return input.substring(5).trim();
        case DEADLINE:
            return input.substring(9).trim().split(" /by ")[0].trim();
        case EVENT:
            return input.substring(6).trim().split(" /from ")[0].trim();
        default:
            throw new BingBongException("Invalid command type for description parsing.");
        }
    }

    public static LocalDateTime parseDeadlineDateTime(String input) throws BingBongException {
        try {
            String by = input.substring(9).trim().split(" /by ")[1].trim();
            return DateTimeHandler.parse(by);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new BingBongException("The deadline format is incorrect. Use: deadline <task> /by <time>");
        }
    }

    public static LocalDateTime[] parseEventDateTime(String input) throws BingBongException {
        try {
            String[] parts = input.substring(6).trim().split(" /from | /to ");
            if (parts.length < 3) {
                throw new BingBongException("The event format is incorrect. Use: event <task> /from <start time> /to <end time>");
            }
            LocalDateTime fromDateTime = DateTimeHandler.parse(parts[1].trim());
            LocalDateTime toDateTime = DateTimeHandler.parse(parts[2].trim());
            return new LocalDateTime[]{fromDateTime, toDateTime};
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date/time format. Please use the format: d/M/yyyy HHmm");
        }
    }
}
