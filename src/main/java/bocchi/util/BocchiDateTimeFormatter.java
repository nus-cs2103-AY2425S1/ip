package bocchi.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A utility class to parse date/time strings.
 * When year is not specified, the current year is used.
 * When date is not specified at all, the current date is used.
 * When second is not specified, 00 is used.
 * When time is not specified at all, 23:59:59 is used.
 */
public class BocchiDateTimeFormatter {

    private static final String[] DATE_FORMATS = {
            "yyyy-M-d",
            "yyyy-MM-dd",
            "yyyy-MMM-dd",
            "yyyy-dd-MMM",

            "yyyy/M/d",
            "yyyy/MM/dd",
            "yyyy/MMM/dd",
            "yyyy/dd/MMM",
    };

    private static final String[] DATE_FORMATS_NO_YEAR = {
            "M-d",
            "MM-dd",

            "M/d",
            "MM/dd",

            "MMM dd",
            "dd MMM",
    };

    private static final String[] TIME_FORMATS = {
            "HH:mm:ss",
            "H:mm:ss",
    };

    private static final String[] TIME_FORMATS_NO_SECOND = {
            "HH:mm",
            "H:mm",
    };

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS;
    private static final List<DateTimeFormatter> DATE_FORMATTERS;
    private static final List<DateTimeFormatter> TIME_FORMATTERS;

    static {
        // Initialize TIME_FORMATTERS
        TIME_FORMATTERS = new ArrayList<>();
        for (String timeFormat : TIME_FORMATS) {
            TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                    .appendPattern(timeFormat)
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDate.now().getMonthValue())
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDate.now().getDayOfMonth())
                    .toFormatter()
                    .withLocale(Locale.ENGLISH));
        }
        for (String timeFormatNoSecond : TIME_FORMATS_NO_SECOND) {
            TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                    .appendPattern(timeFormatNoSecond)
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDate.now().getMonthValue())
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDate.now().getDayOfMonth())
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter()
                    .withLocale(Locale.ENGLISH));
        }

        // Initialize DATE_FORMATTERS
        DATE_FORMATTERS = new ArrayList<>();
        for (String dateFormat : DATE_FORMATS) {
            DATE_FORMATTERS.add(new DateTimeFormatterBuilder()
                    .appendPattern(dateFormat)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
                    .toFormatter()
                    .withLocale(Locale.ENGLISH));
        }
        for (String dateFormatNoYear : DATE_FORMATS_NO_YEAR) {
            DATE_FORMATTERS.add(new DateTimeFormatterBuilder()
                    .appendPattern(dateFormatNoYear)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .toFormatter()
                    .withLocale(Locale.ENGLISH));
        }

        // Initialize DATE_TIME_FORMATTERS
        DATE_TIME_FORMATTERS = new ArrayList<>();
        for (String dateFormat : DATE_FORMATS) {
            for (String timeFormat : TIME_FORMATS) {
                DATE_TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                        .appendPattern(dateFormat + " " + timeFormat)
                        .toFormatter()
                        .withLocale(Locale.ENGLISH));
            }
        }
        for (String dateFormat : DATE_FORMATS) {
            for (String timeFormatNoSecond : TIME_FORMATS_NO_SECOND) {
                DATE_TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                        .appendPattern(dateFormat + " " + timeFormatNoSecond)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .toFormatter()
                        .withLocale(Locale.ENGLISH));
            }
        }
        for (String dateFormatNoYear : DATE_FORMATS_NO_YEAR) {
            for (String timeFormat : TIME_FORMATS) {
                DATE_TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                        .appendPattern(dateFormatNoYear + " " + timeFormat)
                        .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                        .toFormatter()
                        .withLocale(Locale.ENGLISH));
            }
        }
        for (String dateFormatNoYear : DATE_FORMATS_NO_YEAR) {
            for (String timeFormatNoSecond : TIME_FORMATS_NO_SECOND) {
                DATE_TIME_FORMATTERS.add(new DateTimeFormatterBuilder()
                        .appendPattern(dateFormatNoYear + " " + timeFormatNoSecond)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                        .toFormatter()
                        .withLocale(Locale.ENGLISH));
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
                return LocalDateTime.parse(dateTime, formatter);
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

        throw new DateTimeParseException("Invalid date/time format: " + dateTime, dateTime, 0);
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

    public static void main(String[] args) {
        System.out.println(parse("1/1 11:12:19"));
    }
}
