package bocchi.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * A utility class to parse date/time strings.
 */
public class BocchiDateTimeFormatter {

    private static final String[] DATE_FORMATS = {
            "yyyy-M-d",
            "yyyy-MM-dd",
            "yyyy-MMM-dd",
            "yyyy-dd-MMM",
            "M-d",
            "MM-dd",

            "yyyy/M/d",
            "yyyy/MM/dd",
            "yyyy/MMM/dd",
            "yyyy/dd/MMM",
            "M/d",
            "MM/dd",

            "MMM dd",
            "dd MMM",
    };

    private static final String[] TIME_FORMATS = {
            "HH:mm:ss",
            "HH:mm",
            "H:mm:ss",
            "H:mm",
    };

    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS;
    private static final DateTimeFormatter[] DATE_FORMATTERS;
    private static final DateTimeFormatter[] TIME_FORMATTERS;

    static {
        // Initialize TIME_FORMATTERS
        TIME_FORMATTERS = new DateTimeFormatter[TIME_FORMATS.length];
        for (int i = 0; i < TIME_FORMATS.length; i++) {
            TIME_FORMATTERS[i] = new DateTimeFormatterBuilder()
                    .appendPattern(TIME_FORMATS[i])
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDate.now().getMonthValue())
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDate.now().getDayOfMonth())
                    .toFormatter();
        }

        // Initialize DATE_FORMATTERS
        DATE_FORMATTERS = new DateTimeFormatter[DATE_FORMATS.length];
        for (int i = 0; i < DATE_FORMATS.length; i++) {
            DATE_FORMATTERS[i] = DateTimeFormatter.ofPattern(DATE_FORMATS[i]);
            DATE_FORMATTERS[i] = new DateTimeFormatterBuilder()
                    .appendPattern(DATE_FORMATS[i])
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter();
        }

        // Initialize DATE_TIME_FORMATTERS
        DATE_TIME_FORMATTERS = new DateTimeFormatter[DATE_FORMATS.length * TIME_FORMATS.length];
        for (int i = 0; i < DATE_FORMATS.length; i++) {
            for (int j = 0; j < TIME_FORMATS.length; j++) {
                // Default year to current year if not specified.
                DATE_TIME_FORMATTERS[i * TIME_FORMATS.length + j] = new DateTimeFormatterBuilder()
                        .appendPattern(DATE_FORMATS[i] + " " + TIME_FORMATS[j])
                        .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                        .toFormatter();
            }
        }
    }

    /**
     * Parses a date/time string, using predefined formats.
     *
     * @param dateTime The date/time string.
     * @return The LocalDateTime object.
     * @throws DateTimeParseException If the date/time format is invalid against all predefined formats.
     */
    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        // try all possible formats with only time specified
        for (DateTimeFormatter formatter : TIME_FORMATTERS) {
            try {
                // Default date to current date if not specified.
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                // Do nothing.
            }
        }

        // try all possible formats with only date specified
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                // Default time to 00:00:00 if not specified.
                return LocalDate.parse(dateTime, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Do nothing.
            }
        }

        //try all possible formats with both date and time specified
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                // Do nothing.
            }
        }

        throw new DateTimeParseException("Invalid date/time format.", dateTime, 0);
    }

    /**
     * Converts a LocalDateTime object to a string.
     *
     * @param dateTime The LocalDateTime object.
     * @return The string representation of the LocalDateTime object.
     */
    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
