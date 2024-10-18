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
     * If only the date is provided, the time is assumed to be 00:00 (midnight).
     *
     * @param date the string representation of the date and time.
     * @return a LocalDateTime object representing the provided date and time.
     * @throws DateTimeParseException if the date string is not in a valid format.
     */
    public static LocalDateTime convertTime(String date) throws DateTimeParseException {
        String[] arr = date.split(" ");
        if (!dateChecking(arr[0])) {
            throw new DateTimeParseException("Invalid date", arr[0], 0);
        }
        LocalDate res = LocalDate.parse(arr[0]);
        LocalTime time;
        if (arr.length == 2) {
            if (!arr[1].matches("[0-9]+")) {
                throw new DateTimeParseException("Invalid time", arr[1], 0);
            }
            int hr = Integer.parseInt(arr[1].substring(0, 2));
            int min = Integer.parseInt(arr[1].substring(2));
            if (!timeChecking(hr, min)) {
                throw new DateTimeParseException("Invalid time", arr[1], 0);
            }
            time = LocalTime.of(hr, min);
        } else {
            time = LocalTime.MIDNIGHT;
        }
        return LocalDateTime.of(res, time);
    }

    /**
     * Checks if the provided hour and minute are within valid ranges.
     *
     * @param hr the hour to check, should be between 0 and 24 inclusive (00:00 to 24:00).
     * @param min the minute to check, should be between 0 and 59 inclusive.
     * @return true if the hour and minute are within valid ranges, false otherwise.
     */
    public static boolean timeChecking(int hr, int min) {
        boolean correctHr = (hr <= 24 && hr >= 0);
        boolean correctMin = (min <= 59 && min >= 0);
        return correctHr && correctMin;
    }

    /**
     * Checks if the provided date string is in a valid "yyyy-MM-dd" format.
     *
     * @param date the string representation of the date to check.
     * @return true if the date is in a valid format, false otherwise.
     */
    public static boolean dateChecking(String date) {
        String[] arr = date.split("-");
        if (arr.length != 3) {
            return false;
        }
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);
        boolean correctYear = (year > 0);
        boolean correctMonth = (month < 13 && month > 0);
        boolean correctDay = (day < 32 && day > 0);
        return correctYear && correctMonth && correctDay;
    }
}
