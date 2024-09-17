package rizzler.ui.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser for any datetime objects input by the user.
 */
public class DateTimeParser {

    /**
     * Handles conversion of a string in <code>YYYY-MM-DD</code> format to a datetime object.
     * @param inputStr String representing a date in <code>YYYY-MM-DD</code> format.
     * @return <code>LocalDate</code> object representing the same date provided by the user.
     * @throws DateTimeParseException If the user's input string cannot be interpreted as a date.
     */
    public static LocalDate toDatetime(String inputStr) throws DateTimeParseException {
        return LocalDate.parse(inputStr);
    }

    /**
     * Returns a boolean representing whether the <code>DateTimeParser</code> is able to parse
     * this particular input string.
     * @param inputStr User's input string.
     * @return Whether this input string is possible to parse.
     */
    public static boolean canParse(String inputStr) {
        try {
            toDatetime(inputStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns a string formatting a particular datetime.
     * @param inputDate LocalDate object representing the date.
     * @return Nicely formatting string for a particular date.
     */
    public static String toStr(LocalDate inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return inputDate.format(formatter);
    }
}
