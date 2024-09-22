package panorama;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import panorama.exception.InvalidDateException;

/**
 * Provides utility methods for parsing and formatting dates.
 * Supports conversion between string representations and {@code LocalDate} objects.
 */
public class DateParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a string in the format "yyyy-MM-dd" into a {@code LocalDate} object.
     *
     * @param dateStr The date string to be parsed.
     * @return A {@code LocalDate} object representing the parsed date and time.
     * @throws DateTimeParseException If the provided string cannot be parsed into a date.
     */
    public static LocalDate parse(String dateStr) throws InvalidDateException {
        LocalDate result;
        try {
            result = LocalDate.parse(dateStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
        return result;
    }

    /**
     * Returns a string representation of the {@code LocalDate} object in the format "MMM dd yyyy".
     * This format is suitable for displaying to users in the terminal.
     *
     * @param dateTime The {@code LocalDate} object to be formatted.
     * @return A string representing the date and time in the specified output format.
     */
    public static String format(LocalDate dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the {@code LocalDate} object in the format "yyyy-MM-dd".
     * This format is suitable for storing date and time on disk.
     *
     * @param dateTime The {@code LocalDate} object to be formatted.
     * @return A string representing the date and time in the specified storage format.
     */
    public static String formatForHardDisk(LocalDate dateTime) {
        return dateTime.format(INPUT_FORMATTER);
    }
}
