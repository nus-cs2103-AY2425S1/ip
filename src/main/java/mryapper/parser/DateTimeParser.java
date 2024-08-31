package mryapper.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

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

    /*
     * Attempts to parse a date and time from the given string and change to HHmm dd MMM yyyy
     * Returns the string itself if parsing fails
     */
    public static String parseDateTime(String dateTimeString) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy");
                String newString = dateTime.format(newFormatter);
                System.out.println(newString);
                return newString;
            } catch (DateTimeParseException e) {
                // Continue to the next formatter if parsing fails
            }
        }
        return dateTimeString;
    }
}
