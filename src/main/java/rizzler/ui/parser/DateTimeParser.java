package rizzler.ui.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser for any datetime objects input by the user.
 */
public class DateTimeParser {

    /**
     * Static method to handle conversion of a string in <code>YYYY-MM-DD</code> format to a datetime object.
     * @param inputStr String representing a date in <code>YYYY-MM-DD</code> format.
     * @return <code>LocalDate</code> object representing the same date provided by the user.
     * @throws DateTimeParseException If the user's input string cannot be interpreted as a date.
     */
    public static LocalDate to_datetime(String inputStr) throws DateTimeParseException {
        // String[] splitInput = input_str.split(" ");
        LocalDate date = LocalDate.parse(inputStr);
        return date;
    }

    /**
     * Returns a string formatting a particular datetime.
     * @param inputDate LocalDate object representing the date.
     * @return Nicely formatting string for a particular date.
     */
    public static String to_str(LocalDate inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return inputDate.format(formatter);
    }
}
