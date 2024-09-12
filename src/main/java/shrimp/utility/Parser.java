package shrimp.utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

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
        if (userInput.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (userInput.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return CommandType.EVENT;
        } else if (userInput.startsWith("todo")) {
            return CommandType.ADD;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (userInput.startsWith("clear")) {
            return CommandType.CLEAR;
        } else if (userInput.startsWith("find")) {
            return CommandType.FIND;
        } else {
            return CommandType.ERROR;
        }
    }
}
