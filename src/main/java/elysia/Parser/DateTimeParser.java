package elysia.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date strings.
 * Provides a method to parse raw date strings into a formatted date.
 */
public class DateTimeParser {

    /**
     * Parses a raw date string in ISO-8601 format (YYYY-MM-DD) and converts it
     * into a formatted date string of the pattern "MMM dd yyyy".
     *
     * @param rawDate the raw date string to be parsed, expected to be in ISO-8601 format (e.g., "2023-09-21").
     * @return the formatted date string in the pattern "MMM dd yyyy" (e.g., "Sep 21 2023").
     * @throws DateTimeParseException if the raw date string is not in the expected format or cannot be parsed.
     */
    public static String parseDate(String rawDate) throws DateTimeParseException {
        LocalDate parsedDate = LocalDate.parse(rawDate);
        String date = parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date;
    }
}
