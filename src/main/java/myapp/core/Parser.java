package myapp.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import myapp.command.Command;
import myapp.command.DeadlineCommand;
import myapp.command.DeleteCommand;
import myapp.command.EventCommand;
import myapp.command.ExitCommand;
import myapp.command.FindCommand;
import myapp.command.FixedDurationCommand;
import myapp.command.HelpCommand;
import myapp.command.ListCommand;
import myapp.command.ListOnCommand;
import myapp.command.MarkCommand;
import myapp.command.ToDoCommand;
import myapp.command.UnMarkCommand;
import myapp.utils.CommandType;



/**
 * The {@code Parser} class is responsible for parsing user input commands
 * and converting them into {@link Command} objects that the BingBong application can execute.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding {@link Command} object.
     *
     * @param input the user's input string.
     * @return the {@link Command} object that corresponds to the user's input.
     * @throws BingBongException if the command is not recognized or if the input is malformed.
     */
    public static Command parseCommand(String input) throws BingBongException {
        assert input != null : "Input should not be null";
        assert !input.trim().isEmpty() : "Input should not be empty";
        CommandType command = CommandType.fromString(input);

        assert command != null : "Command should not be null";
        switch (command) {
        case BYE:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        case LIST_ON:
            return parseListOnCommand(input);
        case LIST:
            return new ListCommand();
        case FIND:
            return parseFindCommand(input);
        case FIXED_DURATION:
            return parseFixedDurationCommand(input);
        case MARK:
            return parseMarkCommand(input);
        case UNMARK:
            return parseUnmarkCommand(input);
        case DELETE:
            return parseDeleteCommand(input);
        case TODO:
            return parseTodoCommand(input);
        case DEADLINE:
            return parseDeadlineCommand(input);
        case EVENT:
            return parseEventCommand(input);
        case INVALID:
        default:
            throw new BingBongException("Command not recognized. Please try again...");
        }
    }

    private static Command parseListOnCommand(String input) throws BingBongException {
        String date = input.substring(8).trim();
        return new ListOnCommand(date);
    }

    private static Command parseFindCommand(String input) throws BingBongException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new BingBongException("Please state the keyword clearly.");
        }
        return new FindCommand(keyword);
    }

    private static Command parseFixedDurationCommand(String input) throws BingBongException {
        String[] parts = input.substring(6).trim().split("/period");
        if (parts.length < 2) {
            throw new BingBongException("Both a description and a duration must be provided.");
        }

        String description = parts[0].trim();
        String periodString = parts[1].trim();

        // Ensure description is not empty
        if (description.isEmpty()) {
            throw new BingBongException("The description of a fixed duration task cannot be empty.");
        }

        int hours;
        try {
            hours = Integer.parseInt(periodString);
            if (hours <= 0) {
                throw new BingBongException("The duration must be a positive number of hours.");
            }
        } catch (NumberFormatException e) {
            throw new BingBongException("The duration must be a valid number of hours.");
        }
        return new FixedDurationCommand(description, hours);
    }

    private static Command parseMarkCommand(String input) {
        int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(markIndex);
    }

    private static Command parseUnmarkCommand(String input) {
        int unMarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnMarkCommand(unMarkIndex);
    }

    private static Command parseDeleteCommand(String input) {
        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(deleteIndex);
    }

    private static Command parseTodoCommand(String input) throws BingBongException {
        String description = parseDescription(input, CommandType.TODO);
        if (description.isEmpty()) {
            throw new BingBongException("The description of a todo cannot be empty.");
        }
        return new ToDoCommand(description);
    }

    private static Command parseDeadlineCommand(String input) throws BingBongException {
        String description = parseDescription(input, CommandType.DEADLINE);
        LocalDateTime byDateTime = parseDeadlineDateTime(input);
        if (description.isEmpty()) {
            throw new BingBongException("The description of a deadline cannot be empty.");
        }
        return new DeadlineCommand(description, byDateTime);
    }

    private static Command parseEventCommand(String input) throws BingBongException {
        String description = parseDescription(input, CommandType.EVENT);
        LocalDateTime[] dateTimes = parseEventDateTime(input);
        if (description.isEmpty()) {
            throw new BingBongException("The description of an event cannot be empty.");
        }
        return new EventCommand(description, dateTimes[0], dateTimes[1]);
    }

    /**
     * Parses the description part of the input for the specified {@link CommandType}.
     *
     * @param input the user's input string.
     * @param type the type of command (TODO, DEADLINE, or EVENT).
     * @return the description of the task.
     * @throws BingBongException if the command type is invalid or the description cannot be parsed.
     */
    public static String parseDescription(String input, CommandType type) throws BingBongException {
        assert input != null : "Input should not be null";
        assert type != null : "Command type should not be null";

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

    /**
     * Parses the date and time from the user's input for a deadline task.
     *
     * @param input the user's input string.
     * @return a {@link LocalDateTime} object representing the deadline.
     * @throws BingBongException if the date/time format is incorrect or if the input is malformed.
     */
    public static LocalDateTime parseDeadlineDateTime(String input) throws BingBongException {
        try {
            String by = input.substring(9).trim().split(" /by ")[1].trim();
            return DateTimeHandler.parse(by);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new BingBongException("The deadline format is incorrect. Use: deadline <task> /by <time>");
        }
    }

    /**
     * Parses the start and end date/time from the user's input for an event task.
     *
     * @param input the user's input string.
     * @return an array of {@link LocalDateTime} objects where the first element is the start time and
     *      the second element is the end time.
     * @throws BingBongException if the date/time format is incorrect, if the input is malformed,
     *      or if the required parts are missing.
     * */
    public static LocalDateTime[] parseEventDateTime(String input) throws BingBongException {
        try {
            String[] parts = input.substring(6).trim().split(" /from | /to ");
            if (parts.length < 3) {
                throw new BingBongException("The event format is incorrect. "
                        + "Use: event <task> /from <start time> /to <end time>");
            }
            LocalDateTime fromDateTime = DateTimeHandler.parse(parts[1].trim());
            LocalDateTime toDateTime = DateTimeHandler.parse(parts[2].trim());
            return new LocalDateTime[]{fromDateTime, toDateTime};
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date/time format. Please use the format: d/M/yyyy HHmm");
        }
    }
}
