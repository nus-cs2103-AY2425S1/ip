package socchat;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides utility methods for parsing and formatting date and time,
 * as well as tokenizing command strings.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Splits a command string into tokens based on the delimiter " /".
     *
     * @param command the command string to be tokenized
     * @return an array of strings, where each element is a token extracted from the command
     */
    public static String[] tokenizeAdd(String command) {
        String[] strToken = command.split(" /");
        return strToken;
    }

    /**
     * Parses a date string into a LocalDateTime object based on the predefined format.
     *
     * @param date the date string to be parsed
     * @return the corresponding LocalDateTime object
     * @throws SocchatException if the date string does not match the expected format
     */
    public static LocalDateTime parseDate(String date) throws SocchatException {
        try {
            return LocalDateTime.parse(date, FORMATTER);
        } catch (DateTimeException e) {
            throw new SocchatException("Please enter your dateTime as this format --- yyyy-MM-dd HH:mm");
        }

    }

    /**
     * Converts a LocalDateTime object to a string representation based on the predefined format.
     *
     * @param date the LocalDateTime object to be converted
     * @return the string representation of the date in the format "yyyy-MM-dd HH:mm"
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(FORMATTER);
    }
}
