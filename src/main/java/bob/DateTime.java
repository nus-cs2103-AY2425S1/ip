package bob;

import bob.exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Utility class that handles the parsing and formatting of datetime.
 */
public class DateTime {
    private static final DateTimeFormatter INPUT_FORMATTER =
            new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("d[d]/M[M][/uuuu][ HHmm]"))
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("'{'dd-MMM-uuuu HHmm'}'");

    /**
     * Parses the given string with the format: <code>d[d]/M[M][/uuuu][ HHmm]</code> <br>
     * The year and time defaults to the current year and 0000 respectively, if not provided.
     *
     * @param string the string to be parsed
     * @return the LocalDateTime that corresponds to the string
     * @throws InvalidDateTimeException if the given string does not follow the format
     */
    public static LocalDateTime parse(String string) {
        switch (string) {
        case "now":
            return LocalDateTime.now();
        case "tmr":
        case "tomorrow":
            return LocalDateTime.now().plusDays(1);
        default:
            try {
                return LocalDateTime.from(INPUT_FORMATTER.parse(string));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        }
    }

    /**
     * Formats the given LocalDateTime with the format: <code>{dd-MMM-uuuu HHmm}</code> <br>
     * For example, <code>{09-Jun-2024 2200}</code>
     *
     * @param dateTime the datetime to be formatted
     * @return the formatted string
     */
    public static String format(LocalDateTime dateTime) {
        return OUTPUT_FORMATTER.format(dateTime);
    }
}
