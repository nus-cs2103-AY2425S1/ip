package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DateTimeParser {
    // Define the possible date-time formats
    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),       // e.g., 2/12/2019 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),    // e.g., 2019-12-02 18:00
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),    // e.g., 02-12-2019 18:00
            DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"),  // e.g., 12/02/2019 06:00 PM
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),  // e.g., 2019/12/02 18:00:00
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"), //e.g., Dec 02 2019, 6:00 pm [required]
    };

    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Returns a LocalDateTime object based on whether the input dateTimeStr matches one of the given formats. If the
     * format is valid, a LocalDateTime object is returned. If not, an IllegalArgumentException is thrown.
     *
     * @param dateTimeStr The string that contains the user input.
     * @return
     */
    public static LocalDateTime parse(String dateTimeStr) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);  // Try to parse as LocalDateTime
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }
        // If no format matches, throw an exception
        throw new IllegalArgumentException("Date-time string is not in a recognized format: " + dateTimeStr);
    }

    /**
     * Returns a String which represents the date and time in the format specified by a CUSTOM_FORMATTER.
     *
     * @param dateTime The optional LocalDateTime in the Task object.
     * @param str The mandatory String describing a Task's deadline.
     *
     * @return A String representing the deadline of a Task.
     */
    public static String format(LocalDateTime dateTime, String str) {
        if (dateTime == null) {
            return str;
        }
        return dateTime.format(CUSTOM_FORMATTER);
    }
}