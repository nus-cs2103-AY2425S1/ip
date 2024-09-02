package mryapper.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Responsible for parsing the user input into a common date and time format.
 */
public class DateTimeParser {

    private static final DateTimeFormatter STANDARD_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy");
    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy-MM-d"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy/MM/d"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy/MM/d"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy.MM.d"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy.MM.d"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy MMM dd"),
            DateTimeFormatter.ofPattern("[HH:mm ]yyyy MMM d"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy MMM dd"),
            DateTimeFormatter.ofPattern("[HHmm ]yyyy MMM d"),
            DateTimeFormatter.ofPattern("[HH:mm ]dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]d.MM.yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]d.MM.yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]d-MM-yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]d-MM-yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]d/MM/yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]d/MM/yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]dd MMM yyyy"),
            DateTimeFormatter.ofPattern("[HH:mm ]d MMM yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]dd MMM yyyy"),
            DateTimeFormatter.ofPattern("[HHmm ]d MMM yyyy"),
    };

    /**
     * Attempts to parse a date and time and change it to a common format.
     * Returns the input string if the parsing fails.
     *
     * @param dateTimeString The string to be parsed.
     * @return The date and time in "HHmm dd MMM yyyy" format or the input string if parsing fails.
     */
    public static String parseDateTime(String dateTimeString) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                String newString = dateTime.format(STANDARD_FORMAT);
                System.out.println(newString);
                return newString;
            } catch (DateTimeParseException e) {
                // Continue to the next formatter if parsing fails
            }
        }
        return dateTimeString;
    }
}
