package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class to parse Date */
public class DateTime {
    /** A date format */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a date string to LocalDate instance.
     * @param dateString Is a String representation of a date.
     * @return LocalDate instance of date string.
     * @throws DateTimeParseException When date string format is invalid.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, formatter);
    }
}
