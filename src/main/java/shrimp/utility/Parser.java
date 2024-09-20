package shrimp.utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

/**
 * The {@code Parser} class provides methods to parse user commands and format date-time strings.
 * It includes an enum {@code CommandType} to represent the different types of commands supported by the application.
 */
public class Parser {

    /**
     * Enum representing the various command types that can be parsed from user input.
     */
    public enum CommandType {
        LIST, BYE, MARK, UNMARK, ADD, DEADLINE, EVENT, ERROR, DELETE, CLEAR, FIND
    }

    /**
     * A {@code DateTimeFormatter} that defines the pattern for parsing and formatting date-time strings.
     * The pattern supports day/month/year format with optional time (HH:mm or HHmm).
     */
    public static final DateTimeFormatter PATTERN = new DateTimeFormatterBuilder()
            // Day (single or double-digit)
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral('/')
            // Month (single or double-digit)
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral('/')
            // Year (4 digits)
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart().appendLiteral(' ').optionalEnd()
            .optionalStart().appendPattern("HH:mm").optionalEnd()
            .optionalStart().appendPattern("HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    /**
     * Parses the user input to determine the type of command entered.
     *
     * @param userInput The input string entered by the user.
     * @return The {@code CommandType} corresponding to the user input.
     */
    public static CommandType parseCommand(String userInput) {
        assert userInput != null : "userInput is null";
        Map<String, CommandType> commandTypeMap = Map.of(
                "bye", CommandType.BYE,
                "list", CommandType.LIST,
                "mark", CommandType.MARK,
                "unmark", CommandType.UNMARK,
                "deadline", CommandType.DEADLINE,
                "event", CommandType.EVENT,
                "todo", CommandType.ADD,
                "delete", CommandType.DELETE,
                "clear", CommandType.CLEAR,
                "find", CommandType.FIND
        );

        // Loop through map entries and check if the userInput starts with any key
        return commandTypeMap.entrySet().stream()
                .filter(entry -> userInput.startsWith(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(CommandType.ERROR);
    }
}
