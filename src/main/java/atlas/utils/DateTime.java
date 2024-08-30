package atlas.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date time class containing the methods to format date times in various formats.
 */
public class DateTime {
    public static final DateTimeFormatter DATE_TIME_PRINT_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
    public static final DateTimeFormatter DATE_TIME_FILE_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Generates LocalDateTime from the date and time entered by the user.
     *
     * @param dateTime The date and time string entered by the user.
     * @return LocalDateTime The date and time object to be used when creating deadlines or events.
     * @throws DateTimeException The exception thrown if a user enters an invalid date or an uses an invalid format
     */
    public static LocalDateTime formatDate(String dateTime) throws DateTimeException {
        String[] dateTimes = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimes[0]);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(dateTimes[1], timeFormatter);
        return LocalDateTime.of(date, time);
    }
}
