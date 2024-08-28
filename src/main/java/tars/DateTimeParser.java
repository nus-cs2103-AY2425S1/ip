package tars;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    private static final DateTimeFormatter STORED_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        try {
            System.out.println("Attempting to parse date: " + dateTimeString);
            return LocalDateTime.parse(dateTimeString.trim(), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            // If parsing with "yyyy-MM-dd HHmm" fails, try parsing with "dd MMM yyyy, HH:mm"
            System.out.println("Failed to parse with date-time formatter, trying stored format...");
            return LocalDateTime.parse(dateTimeString.trim(), STORED_FORMATTER);
        }
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }
}


