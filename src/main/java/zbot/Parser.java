package zbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that parses and formats date and time.
 */
public class Parser {
    private static final String DEFAULT_TIME = "2359";
    private static final String INPUT_FORMAT = "dd/MM/yyyy HHmm";
    private static final String OUTPUT_FORMAT = "d MMM yyyy HH:mm";

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTime Date and time string to be parsed.
     * @return LocalDateTime object representing the date and time.
     * @throws DateTimeParseException If the date and time string is in an invalid
     *                                format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        String[] dateTimeSplit = dateTime.trim().split(" ");

        if (dateTimeSplit.length == 1) {
            dateTime = dateTime + " " + DEFAULT_TIME;
        }

        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(INPUT_FORMAT));
    }

    /**
     * Formats a LocalDateTime object into a date and time string for output.
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return Date and time string in the output format. (i.e. d MMM yyyy HH:mm)
     */
    public static String formatDateTimeToOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_FORMAT));
    }

    /**
     * Formats a LocalDateTime object into a date and time string for input.
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return Date and time string in the input format. (i.e. dd/MM/yyyy HHmm)
     */
    public static String formatDateTimeToInput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(INPUT_FORMAT));
    }

}
