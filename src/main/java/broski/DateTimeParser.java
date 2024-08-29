package broski;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        // Define the expected format, e.g., dd/MM/yyyy HHmm for "2/12/2019 1800"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String formatOutput(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        return date.format(formatter);
    }
}
