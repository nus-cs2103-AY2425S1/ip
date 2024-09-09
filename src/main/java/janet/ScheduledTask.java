package janet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a schedule (date and time)
 */
public class ScheduledTask extends Task {

    public ScheduledTask(String description, String symbol, LocalDateTime scheduledDate) {
        super(description, symbol, scheduledDate);
    }


    /**
     * Converts and returns the input date and time into appropriate formats
     * date -> MM dd yyyy
     * time -> hh:mm a
     *
     * @param inputDate A string representing a date in the format, yyyy-MM-dd
     * @param inputTime A string representing a time in the format, hh:mm
     * @return A string representing the time and date (MM dd yyyy hh:mm a)
     */
    public static String DateAndTimeFormatter(String inputDate, String inputTime) {
        LocalDate localDate = LocalDate.parse(inputDate);
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        LocalTime localTime = LocalTime.parse(inputTime);
        String time = localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return date + " " + time;
    }
}
