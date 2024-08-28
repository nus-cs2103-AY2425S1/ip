package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Class that parses an input string into a {@code LocalDateTime} or {@code LocalDate}.
 * <p>
 * This can be used by classes which require an input date and time.
 */
public class DateHandler {
    private static final DateTimeFormatter[] formatters = {
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Parses an input string into a c if possible.
     * <p>
     * Otherwise, returns a null {@code LocalDateTime}.
     * @param date The input string to parse.
     * @return {@code LocalDateTime} object.
     */
    public static LocalDateTime parseLocalDateTime(String date) {
        LocalDateTime localDateTime = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                localDateTime = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeException ignored) {}
        }
        return localDateTime;
    }

    /**
     * Parses an input string into a {@code LocalDate} if possible.
     * <p>
     * Otherwise, returns a null {@code LocalDate}.
     * @param date The input string to parse.
     * @return {@code LocalDate} object.
     */
    public static LocalDate parseLocalDate(String date) {
        LocalDate localDate = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                localDate = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeException ignored) {}
        }
        return localDate;
    }
}
