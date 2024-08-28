package Bot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {

    public static String convertDate(String input) {
        if (isDateTimeFormat(input)) {
            return convertDateTime(input);
        } else if (isDateFormat(input)) {
            return formatDate(input);
        } else {
            return "Invalid date format!";
        }
    }

    //Check date timing if its date/M/yyyy/ HourHourMinMin
    private static boolean isDateTimeFormat(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //Check if date format if its yyyy-mm-dd
    private static boolean isDateFormat(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //convert date with timing
    private static String convertDateTime(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);

        String dayWithSuffix = getDayWithSuffix(dateTime.getDayOfMonth());
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM yyyy, h a");
        String formattedDate = dateTime.format(outputFormatter);

        return dayWithSuffix + " of " + formattedDate.toLowerCase();
    }
    // Convert yyyy-mm-dd
    private static String formatDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }

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
