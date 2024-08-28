package PurrfessorDipsy.Parser;

import java.time.LocalDate;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter STORAGE_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateStr, INPUT_DATE_FORMATTER);
        // Validate that the parsed date matches the input string
        if (!date.format(INPUT_DATE_FORMATTER).equals(dateStr)) {
            throw new DateTimeParseException("Invalid date", dateStr, 0);
        }
        return date;
    }

    public static String formatDateForDisplay(LocalDate date) {
        return date.format(DISPLAY_DATE_FORMATTER);
    }

    public static String formatDateForStorage(LocalDate date) {
        return date.format(STORAGE_DATE_TIME_FORMATTER);
    }
}
