package Bunbun.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class implements a DateTime handler to format
 * and check for validity of DateTime objects.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class DateTimeHandler {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns a LocalDate object based on the provided date string,
     * checking validity of the string format and returning null if not.
     *
     * @param dateStr String representing a date, which should be in yyyy-MM-dd format.
     * @return LocalDate based on provided date string, null if invalid.
     */
    public static LocalDate isValidLocalDate(String dateStr) {

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return date;
        }
        return date;
    }
}
