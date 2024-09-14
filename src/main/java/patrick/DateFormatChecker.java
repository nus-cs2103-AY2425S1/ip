package patrick;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods for checking and validating date and time formats.
 * It supports a predefined set of date and time formats and can determine the format of a given date or time string.
 */
public class DateFormatChecker {
    /**
     * A list of predefined date formats that this class can recognize.
     */
    private static final List<String> FORMATS = new ArrayList<>();

    // Static block to initialize the supported date formats
    static {
        FORMATS.add("yyyy-MM-dd HHmm");
        FORMATS.add("dd-MM-yyyy HHmm");
        FORMATS.add("d-MM-yyyy HHmm");
        FORMATS.add("MM-dd-yyyy HHmm");
        FORMATS.add("yyyy/MM/dd HHmm");
        FORMATS.add("dd/MM/yyyy HHmm");
        FORMATS.add("d/MM/yyyy HHmm");
        FORMATS.add("MM/dd/yyyy HHmm");
        FORMATS.add("MMM dd yyyy HHmm");
        FORMATS.add("MMM d yyyy HHmm");
    }

    /**
     * Determines the format of the given date string.
     * It checks the string against a predefined list of date formats and returns the matching format.
     *
     * @param date the date string to check.
     * @return the format of the date string if recognized, otherwise "Unknown Format".
     */
    public static String getDateFormat(String date) {
        for (String format : FORMATS) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                LocalDateTime.parse(date, formatter);
                return format;
            } catch (DateTimeException e) {
                // Ignore and continue checking other formats
            }
        }
        return "Unknown Format";
    }

    /**
     * Determines if the given time string matches the "HHmm" format.
     *
     * @param time the time string to check.
     * @return the "HHmm" format if the time string matches, otherwise "Unknown Format".
     */
    public static String getTimeFormat(String time) {
        String format = "HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalTime.parse(time, formatter);
            return format;
        } catch (DateTimeException e) {
            // Ignore and return "Unknown Format"
        }
        return "Unknown Format";
    }
}
