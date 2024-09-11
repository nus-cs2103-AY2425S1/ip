package dipsy.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility class for parsing and formatting dates in various formats.
 * Provides methods to parse input dates, format dates for display, and format dates for storage.
 */
public class DateParser {

    /** Formatter for input dates in the format "yyyy-MM-dd". */
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Formatter for displaying dates in the format "MMM dd yyyy" (e.g., "Aug 25 2024"). */
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    /** Formatter for storing dates in ISO format "yyyy-MM-dd", so it can be easily retrieved and parsed later. */
    private static final DateTimeFormatter STORAGE_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Parses a date string in the format "yyyy-MM-dd" and returns a LocalDate object.
     * Validates that the parsed date matches the input string to prevent normalization of invalid dates.
     *
     * @param dateStr The date string to parse.
     * @return The parsed LocalDate object.
     * @throws DateTimeParseException If the date string is invalid or does not match the expected format.
     */
    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        assert dateStr != null : "dateStr should not be null";

        LocalDate date = LocalDate.parse(dateStr, INPUT_DATE_FORMATTER);

        // Validate that the parsed date matches the input string due automatic adjustment of invalid dates.
        // (e.g. February 30 might loop to March 1)
        if (!date.format(INPUT_DATE_FORMATTER).equals(dateStr)) {
            throw new DateTimeParseException("Invalid date", dateStr, 0);
        }

        return date;
    }

    /**
     * Formats a LocalDate object for display in the format "MMM dd yyyy".
     *
     * @param date The LocalDate object to format.
     * @return A string representing the formatted date.
     */
    public static String formatDateForDisplay(LocalDate date) {
        assert date != null : "date should not be null";
        return date.format(DISPLAY_DATE_FORMATTER);
    }

    /**
     * Formats a LocalDate object for storage in ISO format "yyyy-MM-dd".
     *
     * @param date The LocalDate object to format.
     * @return A string representing the date in ISO format.
     */
    public static String formatDateForStorage(LocalDate date) {
        assert date != null : "date should not be null";
        return date.format(STORAGE_DATE_TIME_FORMATTER);
    }
}
