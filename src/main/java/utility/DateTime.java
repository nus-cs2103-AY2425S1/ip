package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** A class to parse Date */
public class DateTime {
    /** A date format */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns a LocalDate instance from dateString.
     *
     * @param dateString String representation of a date.
     * @return LocalDate instance of dateString.
     * @throws DateTimeParseException Invalid date format.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, formatter);
    }
}
