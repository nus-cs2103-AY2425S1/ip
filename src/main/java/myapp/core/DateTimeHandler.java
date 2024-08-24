package myapp.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {

    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter
                                                                        .ofPattern("d MMM yyyy, h:mm a");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, INPUT_DATETIME_FORMATTER);
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, INPUT_DATE_FORMATTER);
    }

    public static String format(LocalDateTime dateTime, boolean fileFormat) {
        if (fileFormat) {
            return dateTime.format(INPUT_DATETIME_FORMATTER);
        } else {
            return dateTime.format(OUTPUT_DATETIME_FORMATTER);
        }
    }

    // Overloaded method with default value
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, false);
    }

    public static String format (LocalDate date) {
        return date.format(OUTPUT_DATE_FORMATTER);
    }
}
