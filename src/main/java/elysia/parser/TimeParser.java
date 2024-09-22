package elysia.parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import elysia.exception.InvalidDateTimeFormatException;

/**
 * Parses the string input into LocalTime object.
 */
public class TimeParser {

    /**
     * Parses the string input into LocalTime object.
     *
     * @param timeString
     * @return
     */
    public static LocalTime parseTime(String timeString) throws DateTimeParseException, InvalidDateTimeFormatException {
        LocalTime parsedTime = null;

        DateTimeFormatter[] formatters = getDateTimeFormatters();

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedTime = LocalTime.parse(timeString.toUpperCase(), formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        checkValidTimeInput(parsedTime);

        return parsedTime;
    }

    private static DateTimeFormatter[] getDateTimeFormatters() {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("h:mma"), // 2:30PM
                DateTimeFormatter.ofPattern("h:mm a"), // 2:30 PM
                DateTimeFormatter.ofPattern("HHmm"), // 1430
                DateTimeFormatter.ofPattern("HH:mm"), // 14:30
                DateTimeFormatter.ofPattern("h a"), // 2 PM
                DateTimeFormatter.ofPattern("ha"), // 2PM
                DateTimeFormatter.ofPattern("H") // 14
        };
        return formatters;
    }

    private static void checkValidTimeInput(LocalTime time) throws InvalidDateTimeFormatException {
        if (time == null) {
            throw new InvalidDateTimeFormatException();
        }
    }
}
