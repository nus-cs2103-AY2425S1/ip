package broski.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A simple class to process date and time input by the user.
 */
public class DateTimeParser {

    /**
     * Parses date and time and formats them into another type.
     */
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        // Define the expected format, e.g., dd/MM/yyyy HHmm for "2/12/2019 1800"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Formats the output for console log messages, in particular for deadline and event.
     */
    public String formatOutput(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        return date.format(formatter);
    }
}
