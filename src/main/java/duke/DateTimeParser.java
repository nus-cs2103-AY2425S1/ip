package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateTimeParser {
    public static List<DateTimeFormatter> inputFormatters = new ArrayList<>();
    static {
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., "2/12/2019 1800"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Dec 2 2019, 6:00 PM"
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., "2/12/2019"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Jul 2 2019, 5:00 pm");
    }

    // Helper method to parse a date string with multiple formats
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        for (DateTimeFormatter formatter : DateTimeParser.inputFormatters) {
            try {
                if (Objects.equals(formatter, DateTimeFormatter.ofPattern("d/M/yyyy"))) {
                    LocalDate parsedDate = LocalDate.parse(dateTimeStr, formatter);
                    return parsedDate.atStartOfDay(); // Default to midnight
                } else {
                    return LocalDateTime.parse(dateTimeStr, formatter);
                }
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }
        return null; // If no format matched, return null
    }
}
