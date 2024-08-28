package barry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user input and converting it into executable commands.
 * It parses different types of user commands and provides the appropriate Command object to be executed.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string to parse.
     * @return The Command object that corresponds to the user's input.
     * @throws BarryException If the input does not correspond to any known command, or if there is an error in the input format.
     */
    public static Command parse(String input) throws BarryException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new Command.ExitCommand();
            case "list":
                return new Command.ListCommand();
            case "mark":
                return new Command.MarkCommand(Integer.parseInt(arguments));
            case "unmark":
                return new Command.UnmarkCommand(Integer.parseInt(arguments));
            case "todo":
                return new Command.AddTodoCommand(arguments);
            case "find":
                return new Command.FindCommand(arguments);
            case "deadline":
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) throw new BarryException("The deadline format is incorrect.");
                LocalDateTime byDateTime = parseDateTime(deadlineParts[1].trim());
                return new Command.AddDeadlineCommand(deadlineParts[0].trim(), byDateTime);
            case "event":
                String[] eventParts = arguments.split(" /from ");
                if (eventParts.length < 2) throw new BarryException("The event format is incorrect.");
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) throw new BarryException("The event time format is incorrect.");
                LocalDateTime fromTime = parseDateTime(timeParts[0].trim());
                LocalDateTime toTime = parseDateTime(timeParts[1].trim());
                return new Command.AddEventCommand(eventParts[0].trim(), fromTime, toTime);
            case "delete":
                return new Command.DeleteCommand(Integer.parseInt(arguments));
            default:
                throw new BarryException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a date and time string in the format "d/M/yyyy HHmm" and converts it to a LocalDateTime object.
     *
     * @param dateTimeString The string representing the date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws BarryException If the input string is not in the correct format.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) throws BarryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new BarryException("The date and time format is incorrect. Please use d/M/yyyy HHmm format.");
        }
    }
}
