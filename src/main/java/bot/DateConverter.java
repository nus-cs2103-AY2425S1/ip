package bot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 Responsible for providing methods for formatting user input date string into readable date string format
 */
public class DateConverter {

    /**
     * Converts the given date string to a readable format. It determines if the
     * input is in a datetime format or a date-only format and applies the appropriate conversion.
     *
     * @param input The date string to be converted.
     * @return The formatted date string or an error message if the format is invalid.
     */
    public static String convertDate(String input) {
        System.out.println(input);
        if (isDateTimeFormat(input)) {
            return convertDateTime(input);
        } else if (isDateFormat(input)) {
            return formatDate(input);
        } else {
            return "Invalid date format!";
        }
    }

    /**
     * Checks if the input string is in the "d/M/yyyy HHmm" datetime format.
     *
     * @param input The date string to be checked.
     * @return true if the input matches the datetime format, false otherwise.
     */
    private static boolean isDateTimeFormat(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input string is in the "yyyy-MM-dd" date format.
     *
     * @param input The date string to be checked.
     * @return true if the input matches the date format, false otherwise.
     */
    private static boolean isDateFormat(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    /**
     * Converts a date string in the "d/M/yyyy HHmm" format to a more readable format.
     *
     * @param input The datetime string to be converted.
     * @return The formatted date string with a day suffix and time.
     */
    private static String convertDateTime(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);

        String dayWithSuffix = getDayWithSuffix(dateTime.getDayOfMonth());
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM yyyy, h:mm a");
        String formattedDate = dateTime.format(outputFormatter);

        return dayWithSuffix + " of " + formattedDate.toLowerCase();
    }


    /**
     * Converts a date string in yyyy-MM-dd format into a more readable string format
     * @param input The date string to be converted.
     * @return The formatted date string in "MMM dd yyyy" format.
    */
    private static String formatDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }


    /**
     * Adds the appropriate suffix to a given day of the month (e.g., "1st", "2nd").
     *
     * @param day The day of the month.
     * @return The day with its corresponding suffix.
     */
    private static String getDayWithSuffix(int day) {
        if (day >= 1 && day <= 31) {
            if (day >= 11 && day <= 13) {
                return day + "th";
            }
            switch (day % 10) {
                case 1: return day + "st";
                case 2: return day + "nd";
                case 3: return day + "rd";
                default: return day + "th";
            }
        }
        return String.valueOf(day);
    }
}
