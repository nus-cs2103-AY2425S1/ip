package mryapper.parser;

import mryapper.exception.InvalidSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Responsible for parsing the user input into a common date and time format.
 */
public class DateTimeParser {

    private static final DateTimeFormatter STANDARD_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    public static final String DATE_TIME_SYNTAX = "e.g. 11:24 8-12-2012, 1934 24/1/2000";

    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy"),
            DateTimeFormatter.ofPattern("HHmm d-MM-yyyy"),
            DateTimeFormatter.ofPattern("HH:mm d-M-yyyy"),
            DateTimeFormatter.ofPattern("HHmm d-M-yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd-M-yyyy"),
            DateTimeFormatter.ofPattern("HHmm dd-M-yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("HHmm dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm d/MM/yyyy"),
            DateTimeFormatter.ofPattern("HHmm d/MM/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm d/M/yyyy"),
            DateTimeFormatter.ofPattern("HHmm d/M/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd/M/yyyy"),
            DateTimeFormatter.ofPattern("HHmm dd/M/yyyy")
    };

    /**
     * Attempts to parse a date and time and change it to a common format.
     * Returns the input string if the parsing fails.
     *
     * @param dateTimeString The string to be parsed.
     * @return The date and time in "HHmm dd MMM yyyy" format or the input string if parsing fails.
     */
    public static String parseDateTime(String dateTimeString) throws InvalidSyntaxException {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                return dateTime.format(STANDARD_FORMAT);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter if parsing fails
            }
        }
        throw new InvalidSyntaxException("You need to give me the date/time in the proper format",
                DATE_TIME_SYNTAX);
    }
}
