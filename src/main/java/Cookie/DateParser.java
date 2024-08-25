package Cookie;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    /**
     * Checks whether a given string is parsable by LocalDate.parse.
     *
     * @param date to be checked.
     * @return true if parsable and false otherwise.
     */
    public static boolean checkValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns a LocalDate format of a given date.
     *
     * @param date to convert.
     * @return LocalDate if parsable, otherwise null.
     */

    public static LocalDate convertStringToDate(String date) {
        if (DateParser.checkValidDate(date)) {
            return LocalDate.parse(date);
        }
        return null;
    }

    /**
     * Changes the pattern of a LocalDate
     *
     * @param date to change pattern
     * @return a string with the format "Month Date Year"
     */
    public static String changePattern(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
