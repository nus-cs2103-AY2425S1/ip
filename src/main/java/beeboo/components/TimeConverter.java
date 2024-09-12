package beeboo.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The TimeConverter class provides functionality to convert a string
 * representation of date and time into a LocalDateTime object.
 */
public class TimeConverter {

    /**
     * Converts a string representation of a date and time into a LocalDateTime object.
     * The string should be in the format "yyyy-MM-dd HHmm" or "yyyy-MM-dd".
     * If only the date is provided, the time is assumed to be 00:00.
     *
     * @param date the string representation of the date and time
     * @return a LocalDateTime object representing the provided date and time
     * @throws DateTimeParseException if the date string is not in a valid format
     */
    public static LocalDateTime convertTime(String date) throws DateTimeParseException {
        String[] arr = date.split(" ");
        LocalDate res = LocalDate.parse(arr[0]);
        LocalTime time;
        if (arr.length == 2) {
            time = LocalTime.of(Integer.parseInt(arr[1].substring(0, 2)),
                    Integer.parseInt(arr[1].substring(2)));
        } else {
            time = LocalTime.MIDNIGHT;
        }
        return LocalDateTime.of(res, time);
    }
}
