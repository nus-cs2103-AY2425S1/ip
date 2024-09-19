package bob;

import bob.exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateTime {
    private static final DateTimeFormatter INPUT_FORMATTER =
            new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("d[d]/M[M][/uuuu][ HHmm]"))
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("'{'dd-MMM-uuuu HHmm'}'");

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

    public static String format(LocalDateTime dateTime) {
        return OUTPUT_FORMATTER.format(dateTime);
    }
}
