package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParseTasks {

    /**
     * Parses a time string in the format "HH:mm" (e.g., "16:00") and returns
     * a `LocalTime` object.
     *
     * @param time The time string to be parsed.
     * @return A `LocalTime` object representing the parsed time.
     */
    public static LocalTime parseTime(String time) {

        //input of format 16:00
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(time, timeFormatter);
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" (e.g., "2020-12-10") and returns
     * a `LocalDate` object.
     *
     * @param date The date string to be parsed.
     * @return A `LocalDate` object representing the parsed date.
     */
    public static LocalDate parseDateFormat1(String date) {

        // input of format 2020-12-10
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * Parses a date string in the format "dd/MM/yyyy" (e.g., "10/12/2020") and returns
     * a `LocalDate` object.
     *
     * @param date The date string to be parsed.
     * @return A `LocalDate` object representing the parsed date.
     */
    public static LocalDate parseDateFormat2(String date) {

        // input of format 10/12/2020
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * Parses a date and time string in the format "yyyy-MM-dd HH:mm"
     * (e.g., "2020-12-10 16:00") and returns a `LocalDateTime` object.
     *
     * @param dateTime The date and time string to be parsed.
     * @return A `LocalDateTime` object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTimeFormat1(String dateTime) {

        // input of format 2020-12-10 16:00
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    /**
     * Parses a date and time string in the format "dd/MM/yyyy HH:mm"
     * (e.g., "10/12/2020 16:00") and returns a `LocalDateTime` object.
     *
     * @param dateTime The date and time string to be parsed.
     * @return A `LocalDateTime` object representing the parsed date and time.
     */
    public static LocalDateTime parseDateTimeFormat2(String dateTime) {

        // input of format 10/12/2020 16:00
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }
}