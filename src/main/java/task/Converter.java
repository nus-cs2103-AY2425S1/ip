package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deal with converting between datetime and string
 */

public class Converter {
    /**
     * Gets datetime from input (from user)
     * 
     * @param input Input from user
     * @return the parsed DateTime
     */
    public static LocalDateTime InputToDateTime(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets formatted string format from datetime
     * 
     * @param dateTime the datetime to format
     * @return The converted String
     */
    public static String DateTimeToOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    /**
     * Gets the datetime back from output
     * 
     * @param output The string using output format (.e.g from storage)
     * @return The retrieve datetime
     */
    public static LocalDateTime OutputToDateTime(String output) {
        return LocalDateTime.parse(output, DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }
}
