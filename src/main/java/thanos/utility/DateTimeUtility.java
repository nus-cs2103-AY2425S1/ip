package thanos.utility;

import static thanos.ui.Ui.printDivider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     */
    public static LocalDateTime parse(String s) {
        // Try parsing as LocalDateTime
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        // Try parsing as LocalDate if LocalDateTime parsing fails
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(s, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        // If all parsing attempts fail, print an error message and return null
        System.out.println("Invalid datetime format: " + s);
        printDivider();
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
