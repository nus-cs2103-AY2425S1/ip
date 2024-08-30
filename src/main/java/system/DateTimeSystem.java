package system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeSystem {

    /**
     * Creates a LocalDateTime object from the provided date and time components.
     * The components include year, month, day, hour, and minute, all provided as strings.
     * These strings are parsed into integers to construct the LocalDateTime object.
     *
     * @param y Year as a string.
     * @param m Month as a string.
     * @param d Day of the month as a string.
     * @param h Hour of the day as a string.
     * @param min Minute of the hour as a string.
     * @return a LocalDateTime object representing the date and time.
     */
    public LocalDateTime createDate(String y, String m, String d, String h, String min) {
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        int hour = Integer.parseInt(h);
        int minute = Integer.parseInt(min);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Formats the LocalDateTime object into a string representation based on a pattern, "yyyy-MM-dd HH:mm a".
     * The pattern specifies this format, year-month-day hour:minute AM/PM.
     *
     * @param ldt LocalDateTime object to be formatted.
     * @return String representation of the formatted date and time.
     */
    public String format(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");

        return ldt.format(formatter);
    }
}
