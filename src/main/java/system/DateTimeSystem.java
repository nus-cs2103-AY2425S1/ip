package system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This DateTimeSystem class creates and formats LocalDateTime objects,
 */
public class DateTimeSystem {
    /**
     * Creates a LocalDateTime object from the provided date and time components.
     * The components include year, month, day, hour, and minute, all provided as strings.
     * These strings are parsed into integers to construct the LocalDateTime object.
     *
     * @param y   Year as a string.
     * @param m   Month as a string.
     * @param d   Day of the month as a string.
     * @param h   Hour of the day as a string.
     * @param min Minute of the hour as a string.
     * @return a LocalDateTime object representing the date and time.
     */
    public LocalDateTime createDateTime(String y, String m, String d, String h, String min) {
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        int hour = Integer.parseInt(h);
        int minute = Integer.parseInt(min);

        return LocalDateTime.of(year, month, day, hour, minute);
    }
    /**
     * Creates a LocalDate object from the provided date components.
     * The components include year, month, and day, all provided as strings.
     * These strings are parsed into integers to construct the LocalDate object.
     *
     * @param y   Year as a string.
     * @param m   Month as a string.
     * @param d   Day of the month as a string.
     * @return a LocalDate object representing the specified date.
     */
    public LocalDate createDate(String y, String m, String d) {
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        return LocalDate.of(year, month, day);
    }
    /**
     * Formats the LocalDate object into a string representation based on a pattern, "yyyy-MM-dd".
     * The pattern specifies this format, year-month-day.
     *
     * @param localDate LocalDate object to be formatted.
     * @return String representation of the formatted date.
     */
    public String formatLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return localDate.format(formatter);
    }

    /**
     * Formats the LocalDateTime object into a string representation based on a pattern, "yyyy-MM-dd HH:mm a".
     * The pattern specifies this format, year-month-day hour:minute AM/PM.
     *
     * @param localDateTime LocalDateTime object to be formatted.
     * @return String representation of the formatted date and time.
     */
    public String formatLocalTimeDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");

        return localDateTime.format(formatter);
    }

    /**
     * Compares current date with the LocalDateTime object parsed into the method.
     *
     * @param localDateTime LocalDateTime object to be compared.
     * @return boolean True/False if current date is before the date parsed in.
     */
    public boolean compareDateTime(LocalDateTime localDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isBefore(localDateTime);
    }
}
