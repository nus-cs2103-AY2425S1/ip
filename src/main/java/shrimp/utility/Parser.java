package shrimp.utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Parser {
    public enum CommandType {
        LIST, BYE, MARK, UNMARK, ADD, DEADLINE, EVENT, ERROR, DELETE, CLEAR
    }

    public static final DateTimeFormatter PATTERN = new DateTimeFormatterBuilder()
            // Day (single or double-digit)
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral('/')
            // Month (single or double-digit)
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral('/')
            // Year (4 digits)
            .appendValue(ChronoField.YEAR, 4)
            .appendLiteral(' ')
            .optionalStart().appendPattern("HH:mm").optionalEnd()
            .optionalStart().appendPattern("HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public static CommandType parseCommand(String userInput) {
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
        }else {
            return CommandType.ERROR;
        }
    }
}
