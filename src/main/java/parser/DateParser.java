package parser;

import socchat.SocchatException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a date string into a LocalDateTime object based on the predefined format.
     *
     * @param date the date string to be parsed
     * @return the corresponding LocalDateTime object
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
     * Converts a LocalDateTime object to a string representation based on the predefined format.
     *
     * @param date the LocalDateTime object to be converted
     * @return the string representation of the date in the format "yyyy-MM-dd HH:mm"
     */
    public static String dateToString(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static void checkEndDate(LocalDate start, LocalDate end) throws SocchatException {
        if (start.isAfter(end)) {
            throw new SocchatException("Meow~ Start date cannot be after end date");
        }
    }
}
