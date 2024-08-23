import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static CommandType parseCommand(String input) {
        return CommandType.fromString(input);
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
