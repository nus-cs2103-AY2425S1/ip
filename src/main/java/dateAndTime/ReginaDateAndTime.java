package dateAndTime;

import tasks.ReginaException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The ReginaDateAndTime class represents a date and/or time,
 * providing functionality to format these values.
 */
public class ReginaDateAndTime {
    private static final String INPUT_DATE_PATTERN = "d/M/yyyy";
    private static final String INPUT_TIME_PATTERN = "HHmm";
    private static final String OUTPUT_DATE_PATTERN = "MMM dd yyyy";
    private static final String OUTPUT_TIME_PATTERN = "h.mm a";

    private LocalDate date;
    private LocalTime time;
    private String savedFormat;

    /**
     * Constructs a ReginaDateAndTime instance with the specified date and time.
     *
     * @param dateAndTime The date and time in the format 'd/M/yyyy HHmm'.
     */
    public ReginaDateAndTime(String dateAndTime) throws ReginaException {
        this.savedFormat = dateAndTime;
        String[] dateTime = dateAndTime.split(" ");
        if (dateTime.length < 2) {
            throw new ReginaException("Ehh you need to write BOTH the date and time");
        }

        String dateString = dateTime[0];
        String timeString = dateTime[1];

        try {
            this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
            this.time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(INPUT_TIME_PATTERN));
        } catch (DateTimeParseException e) {
            throw new ReginaException("Invalid date or time format provided.");
        }
    }

    /**
     * Returns a formatted string representing the current date and time.
     * The date is formatted according to the DATE_PATTERN, and the time
     * is formatted according to the TIME_PATTERN.
     *
     * @return A string in the format: "It is <formatted_date>, <formatted_time> currently".
     */
    public static String now() {
        String nowDate = LocalDate.now().format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
        String nowDayOfWeek = LocalDate.now().getDayOfWeek().name();
        String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern(OUTPUT_TIME_PATTERN));
        return String.format("Today is %s, %s, and the time is currently %s", nowDayOfWeek, nowDate, nowTime);
    }

    /**
     * Returns the day of the week for the stored date.
     *
     * @return The day of the week as a string.
     */
    public String getDayOfWeek() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString(); // Returns in uppercase (e.g., MONDAY)
    }

    /**
     * Returns the month of the stored date.
     *
     * @return The month as an integer (1-12).
     */
    public int getMonth() {
        return date.getMonthValue(); // Returns month as an integer (1-12)
    }

    /**
     * Returns the year of the stored date.
     *
     * @return The year as an integer.
     */
    public int getYear() {
        return date.getYear(); // Returns year as an integer
    }

    /**
     * Returns the day of the month for the stored date.
     *
     * @return The day of the month as an integer (1-31).
     */
    public int getDayOfMonth() {
        return date.getDayOfMonth(); // Returns day of the month as an integer (1-31)
    }

    /**
     * Returns the formatted date as a string based on the DATE_PATTERN.
     *
     * @return A formatted string representing the date.
     */
    private String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
    }

    /**
     * Returns the formatted time as a string based on the TIME_PATTERN.
     *
     * @return A formatted string representing the time.
     */
    private String formattedTime() {
        return this.time.format(DateTimeFormatter.ofPattern(OUTPUT_TIME_PATTERN));
    }

    public String toSavedFormatting() {
        return this.savedFormat;
    }

    /**
     * Returns a string representation of the date and time.
     *
     * @return A string in the format: "MMM dd yyyy hh:mm a" or "MMM dd yyyy" if time is not provided.
     */
    @Override
    public String toString() {
        return formattedDate() + ", " +  (this.time != null ? " " + formattedTime() : ""); // Combine formatted date and time
    }
}
