package thanos.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import thanos.exceptions.InvalidDateException;

/**
 * Utility class for parsing and formatting date and time values.
 * <p>
 * The {@code DateTimeUtility} class provides static methods for parsing strings into {@code LocalDateTime}
 * objects using multiple date and time formats, as well as formatting {@code LocalDateTime} objects
 * into a specific string format.
 * </p>
 */
public class DateTimeUtility {
    /**
     * An array of {@code DateTimeFormatter} objects used for parsing date and time strings.
     * These formatters support various date and time patterns including different separators and time formats.
     */
    private static final DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
    };

    /**
     * An array of {@code DateTimeFormatter} objects used for parsing date-only strings.
     * These formatters support various date patterns without time components.
     */
    private static final DateTimeFormatter[] dateFormatters = {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    /**
     * The {@code DateTimeFormatter} used for formatting {@code LocalDateTime} objects into strings.
     * This formatter uses the pattern "MMM dd yyyy HH:mm" for a readable output format.
     */
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses a string into a {@code LocalDateTime} object using multiple date and time formats.
     * <p>
     * This method attempts to parse the input string using various date and time format patterns.
     * If parsing as a {@code LocalDateTime} fails, it attempts to parse the string as a {@code LocalDate} and
     * convert it to a {@code LocalDateTime} at the start of the day.
     * </p>
     *
     * @param s the string to parse.
     * @return the parsed {@code LocalDateTime} object, or {@code null} if parsing fails.
     * @throws InvalidDateException if the string cannot be parsed into a valid {@code LocalDateTime} or
     *                              {@code LocalDate} using the predefined formats.
     */
    public static LocalDateTime parse(String s) throws InvalidDateException {
        // Try parsing as LocalDateTime
        LocalDateTime dateTime = parseStringAsDateTime(s);
        if (dateTime != null) {
            return dateTime;
        }

        // If LocalDateTime parsing fails, try parsing as LocalDate
        LocalDateTime date = parseStringAsDate(s);
        if (date != null) {
            return date;
        }

        // If all parsing attempts fail, throw an InvalidDateException
        throw new InvalidDateException("Invalid datetime format: " + s);
    }

    /**
     * Attempts to parse the given string into a {@code LocalDateTime} using predefined
     * {@code DateTimeFormatter} instances for date-time formats. If the string can be parsed
     * as a {@code LocalDateTime}, it returns the corresponding value.
     *
     * <p>If none of the formatters can parse the string, this method returns {@code null}.
     *
     * @param s the string to parse, expected to be in a date-time format.
     * @return the parsed {@code LocalDateTime} or {@code null} if no matching format is found.
     */
    private static LocalDateTime parseStringAsDateTime(String s) {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        return null;
    }

    /**
     * Attempts to parse the given string into a {@code LocalDateTime} using predefined
     * {@code DateTimeFormatter} instances for date formats. If the string can be parsed
     * as a {@code LocalDate}, it returns the corresponding {@code LocalDateTime} set at
     * the start of the day.
     *
     * <p>If none of the formatters can parse the string, this method returns {@code null}.
     *
     * @param s the string to parse, expected to be in a date format.
     * @return the parsed {@code LocalDateTime} representing the start of the day, or {@code null}
     *         if no matching format is found.
     */
    private static LocalDateTime parseStringAsDate(String s) {
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(s, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        return null;
    }

    /**
     * Formats a {@code LocalDateTime} object into a string using the output format.
     * <p>
     * This method converts a {@code LocalDateTime} to a string representation using the pattern
     * "MMM dd yyyy HH:mm".
     * </p>
     *
     * @param date the {@code LocalDateTime} object to format.
     * @return the formatted string representation of the {@code LocalDateTime}.
     */
    public static String format(LocalDateTime date) {
        return date.format(outputFormatter);
    }
}
