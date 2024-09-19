package parser;

import socchat.SocchatException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateParser} class provides utility methods to parse and format dates.
 * It ensures that dates are strictly validated using a predefined format {@code yyyy-MM-dd}.
 * It also provides methods to check the validity of a date range.
 */
public class DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a date string into a LocalDate object based on the predefined format.
     *
     * @param date the date string to be parsed
     * @return the corresponding LocalDate object
     * @throws SocchatException if the date string does not match the expected format
     */
    public static LocalDate parseDate(String date) throws SocchatException {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeException e) {
            throw new SocchatException("Please enter your date as this format --- yyyy-MM-dd");
        }

    }

    /**
     * Converts a LocalDate object to a string representation based on the predefined format.
     *
     * @param date the LocalDate object to be converted
     * @return the string representation of the date in the format "yyyy-MM-dd"
     */
    public static String dateToString(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Checks that the start date is not after the end date.
     *
     * @param start the start date.
     * @param end   the end date.
     * @throws SocchatException if the start date is after the end date
     */
    public static void checkEndDate(LocalDate start, LocalDate end) throws SocchatException {
        if (start.isAfter(end)) {
            throw new SocchatException("Meow~ Start date cannot be after end date");
        }
    }
}
