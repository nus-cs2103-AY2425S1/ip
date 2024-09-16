package monique.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import monique.exception.IllegalDateFormatException;
//probably need to fix some errors related to parsing of dates of this format "deadline description /by 11/9/2024 6pm"

/**
 * The <code>DateParser</code> class provides functionality to parse and interpret date and time
 * strings into {@link LocalDateTime} objects. It supports various date formats and can handle
 * specific cases such as "tomorrow" with optional times.
 */
public class DateParser {
    public static final String DATE_PATTERN =
            "\\b(\\d{1,2}[-/]\\d{1,2}[-/]\\d{4})\\b|\\b([Mm]on(?:day)?|[Tt]ue(?:sday)?|[Ww]ed(?:nesday)?|[Tt]hu(?:rsday"
            + ")?|[Ff]ri(?:day)?|[Ss]at(?:urday)?|[Ss]un(?:day)?|[Tt]omorrow)\\b";
    public static final String TIME_PATTERN =
            "(?:(?:\\d{1,2}[-/]\\d{1,2}[-/]\\d{4})|(?:[Mm]on(?:day)?|[Tt]ue(?:sday)?|[Ww]ed(?:nesday)?|[Tt]hu(?:rsday)?"
            + "|[Ff]ri(?:day)?|[Ss]at(?:urday)?|[Ss]un(?:day)?|[Tt]omorrow))\\s+((\\d{1,2}:?\\d{0,2}\\s?(?:am|pm)?)"
            + "|(\\b\\d{4}\\b))";
    public static final int WEEK_OFFSET = 7;
    public static final DateTimeFormatter DATE_FORMATTER_SLASH = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter DATE_FORMATTER_DASH = DateTimeFormatter.ofPattern("d-M-yyyy");
    public static final DateTimeFormatter FORMAT_24_HOUR = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter FORMAT_24_HOUR_COLON = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMAT_24_HOUR_3_DIGIT_COLON = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMAT_12_HOUR = DateTimeFormatter.ofPattern("h:ma");
    public static final DateTimeFormatter FORMAT_12_HOUR_3_DIGIT_NO_COLON = DateTimeFormatter.ofPattern("hma");
    public static final DateTimeFormatter FORMAT_12_HOUR_NO_MINUTES = DateTimeFormatter.ofPattern("ha");



    /**
     * Enum for supported date formats.
     */
    public enum DateFormatType {
        TYPE1_SLASH("d[/]M[/]yyyy[ HHmm]"),
        TYPE1_DASH("d[-]M[-]yyyy[ HHmm]");

        private final String pattern;

        DateFormatType(String pattern) {
            this.pattern = pattern;
        }

        /**
         * Retrieves the pattern associated with the date format type.
         *
         * @return The pattern string.
         */
        public String getPattern() {
            return pattern;
        }

        /**
         * Determines the appropriate {@link DateFormatType} based on the provided date string.
         *
         * @param date The date string to check.
         * @return The {@link DateFormatType} that matches the format of the date string.
         * @throws IllegalDateFormatException If the date string does not match any known format.
         */
        public static DateFormatType getType(String date) throws IllegalDateFormatException {
            if (date.contains("/")) {
                return TYPE1_SLASH;
            } else if (date.contains("-")) {
                return TYPE1_DASH;
            } else {
                throw new IllegalDateFormatException();
            }
        }
    }
    /**
     * Enum for days of the week, mapping to {@link DayOfWeek} enum.
     */
    public enum DayOfWeekEnum {
        MONDAY(DayOfWeek.MONDAY),
        TUESDAY(DayOfWeek.TUESDAY),
        WEDNESDAY(DayOfWeek.WEDNESDAY),
        THURSDAY(DayOfWeek.THURSDAY),
        FRIDAY(DayOfWeek.FRIDAY),
        SATURDAY(DayOfWeek.SATURDAY),
        SUNDAY(DayOfWeek.SUNDAY);

        private final DayOfWeek dayOfWeek;

        DayOfWeekEnum(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        /**
         * Retrieves the {@link DayOfWeek} associated with this enum constant.
         *
         * @return The {@link DayOfWeek} instance.
         */
        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        /**
         * Converts a string representation of a day of the week to its corresponding {@link DayOfWeekEnum}.
         *
         * @param day The string representation of the day.
         * @return The matching {@link DayOfWeekEnum}.
         * @throws IllegalDateFormatException If the string does not match any known day of the week.
         */
        public static DayOfWeekEnum fromString(String day) throws IllegalDateFormatException {
            switch (day.toLowerCase()) {
            case "mon": case "monday": return MONDAY;
            case "tue": case "tuesday": return TUESDAY;
            case "wed": case "wednesday": return WEDNESDAY;
            case "thu": case "thursday": return THURSDAY;
            case "fri": case "friday": return FRIDAY;
            case "sat": case "saturday": return SATURDAY;
            case "sun": case "sunday": return SUNDAY;
            default: throw new IllegalDateFormatException();
            }
        }
    }


    private static LocalDate getNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        int daysUntilNext = dayOfWeek.getValue() - today.getDayOfWeek().getValue();
        if (daysUntilNext < 0) {
            daysUntilNext += WEEK_OFFSET;
        }
        return today.plusDays(daysUntilNext);
    }


    public static String getDateString(String input) throws IllegalDateFormatException {
        Pattern datePattern = Pattern.compile(DATE_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher dateMatcher = datePattern.matcher(input);

        if (dateMatcher.find()) {
            // Check which group matched: either the date or the day name
            if (dateMatcher.group(1) != null) {
                return dateMatcher.group(1); // Return the matched date (MM/DD/YYYY)
            } else if (dateMatcher.group(2) != null) {
                return dateMatcher.group(2); // Return the matched day or "tomorrow"
            }
        } else {
            throw new IllegalDateFormatException();
        } // No date or day string found
        return null;
    }


    //get time string
    public static String getTimeString(String input) {
        Pattern timePattern = Pattern.compile(TIME_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher timeMatcher = timePattern.matcher(input);

        if (timeMatcher.find()) {
            return timeMatcher.group(1); // Return the first capturing group (time part)
        }
        return null; // No time string found
    }

    /**
     * Checks if the given string contains a time component.
     * <p>
     * This method identifies time formats such as:
     * <ul>
     *   <li>Standard 12-hour format with "am" or "pm", e.g., "5pm", "12:15am", "520pm".</li>
     *   <li>Shorthand time formats without "am/pm", e.g., "520" or "1720".</li>
     *   <li>24-hour military time format, e.g., "0500", "1720".</li>
     * </ul>
     * <p>
     * The method uses regular expressions to detect the presence of these time patterns in the input string.
     *
     * @param input the input string to check for a time component
     * @return {@code true} if the string contains a valid time component, otherwise {@code false}
     */
    public static boolean hasTime(String input) {
        Pattern timePattern = Pattern.compile(TIME_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher timeMatcher = timePattern.matcher(input);
        return timeMatcher.find(); // Returns true if a time component is found
    }

    public static LocalDateTime getDateTimeString(String string) throws IllegalDateFormatException {
        string = string.trim().toLowerCase();

        // Extract date part and time part
        String dateString = getDateString(string);
        LocalDate datePart = parseDateOrDay(dateString);
        LocalTime timePart = null;

        if (hasTime(string)) {
            try {
                // Extract time substring from input
                String timeString = getTimeString(string); // You need to implement this method
                timePart = parseTime(timeString); // Assuming this method is implemented correctly
            } catch (IllegalDateFormatException e) {
                throw new IllegalDateFormatException();
            }
        } else {
            // Default to start of the day if no time is specified
            timePart = LocalTime.MIDNIGHT;
        }

        return LocalDateTime.of(datePart, timePart);
    }


    /**
     * Parses a given date string into a {@link LocalDate} object.
     * This method supports two date formats: one with slash ("/") separators and one with dash ("-") separators.
     * If the date string cannot be parsed using either format, an {@link IllegalDateFormatException} is thrown.
     *
     * @param dateString The string representing the date to be parsed.
     *                   The expected formats are "MM/dd/yyyy" or "MM-dd-yyyy".
     * @return The parsed {@link LocalDate} object.
     * @throws IllegalDateFormatException If the date string does not match either the slash or dash format.
     */
    public static LocalDate parseDate(String dateString) throws IllegalDateFormatException {
        try {
            // Try to parse date with slash separators
            return LocalDate.parse(dateString, DATE_FORMATTER_SLASH);
        } catch (DateTimeParseException e) {
            try {
                // Try to parse date with dash separators
                return LocalDate.parse(dateString, DATE_FORMATTER_DASH);
            } catch (DateTimeParseException ex) {
                throw new IllegalDateFormatException();
            }
        }
    }

    /**
     * Returns the next occurrence of the specified {@link DayOfWeek} from the current date.
     * If the given day of the week is today, it returns the occurrence of that day in the following week.
     *
     * @param dayOfWeek The {@link DayOfWeek} to find the next occurrence of.
     * @return The {@link LocalDate} representing the next occurrence of the specified day.
     */
    public static LocalDate getNextDateOfWeek(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        int daysUntilNext = (dayOfWeek.getValue() - today.getDayOfWeek().getValue() + WEEK_OFFSET) % WEEK_OFFSET;
        if (daysUntilNext == 0) {
            daysUntilNext = WEEK_OFFSET; // If it's today, get the next occurrence
        }
        return today.plusDays(daysUntilNext);
    }

    /**
     * Parses a string to return a {@link LocalDate}, interpreting it as either a specific date or a day of the week.
     * If the input is "tomorrow", it returns the date for the next day.
     * If the input is a day of the week (e.g., "Monday", "Mon"), it returns the next occurrence of that day.
     * If the input is a date string (e.g., "12/31/2024" or "12-31-2024"), it parses it as a date.
     *
     * @param dateString The string to be parsed, which can be a day of the week, "tomorrow", or a date.
     * @return A {@link LocalDate} representing the parsed date or the next occurrence of the day of the week.
     * @throws IllegalDateFormatException If the input cannot be parsed as a valid day or date.
     */
    public static LocalDate parseDateOrDay(String dateString) throws IllegalDateFormatException {
        dateString = dateString.trim().toLowerCase();

        if ("tomorrow".equals(dateString)) {
            return LocalDate.now().plusDays(1);
        }

        try {
            // Check if the dateString represents a day of the week
            DayOfWeekEnum dayOfWeekEnum = DayOfWeekEnum.fromString(dateString);
            return getNextDateOfWeek(dayOfWeekEnum.getDayOfWeek());
        } catch (IllegalDateFormatException e) {
            // Otherwise, treat it as a date
            return parseDate(dateString);
        }
    }

    /**
     * Parses a time string and returns a {@link LocalTime} object.
     * The method handles several formats including:
     * <ul>
     *   <li>12-hour format with AM/PM and without minutes (e.g., "6am", "10am")</li>
     *   <li>12-hour format with AM/PM and colon (e.g., "7:45am", "10:30pm")</li>
     *   <li>12-hour format with AM/PM and no colon (e.g., "745am", "1045pm")</li>
     *   <li>24-hour format with and without a colon (e.g., "1700", "17:00")</li>
     * </ul>
     *
     * @param timeString The string to be parsed, representing a time in various formats.
     * @return A {@link LocalTime} object representing the parsed time.
     * @throws IllegalDateFormatException If the time string does not match any supported format or cannot be parsed.
     */
    public static LocalTime parseTime(String timeString) throws IllegalDateFormatException {
        try {
            // Clean up the input string
            timeString = sanitizeTimeString(timeString);

            if (is12HourWithAmPmNoMinutes(timeString)) {
                return parse12HourNoMinutes(timeString);
            } else if (is12HourWithColonAmPm(timeString)) {
                return parse12HourWithColon(timeString);
            } else if (is12HourWithoutColonAmPm(timeString)) {
                return parse12HourWithoutColon(timeString);
            } else if (is12HourWithoutColonFourDigitAmPm(timeString)) {
                return parse12HourWithoutColonFourDigit(timeString);
            } else if (is24HourSingleDigitWithColon(timeString)) {
                return parse24HourSingleDigitWithColon(timeString);
            } else if (is24HourWithoutColon(timeString)) {
                return parse24HourWithoutColon(timeString);
            } else if (is24HourWithColon(timeString)) {
                return parse24HourWithColon(timeString);
            }

            // If no format matched, throw an exception
            throw new IllegalDateFormatException();
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException();
        }
    }

    private static String sanitizeTimeString(String timeString) {
        return timeString.trim().toLowerCase().replaceAll("\\s+", "");
    }

    private static boolean is12HourWithAmPmNoMinutes(String timeString) {
        return timeString.matches("\\d{1,2}\\s?(am|pm)");
    }

    private static LocalTime parse12HourNoMinutes(String timeString) {
        return LocalTime.parse(timeString, FORMAT_12_HOUR_NO_MINUTES);
    }

    private static boolean is12HourWithColonAmPm(String timeString) {
        return timeString.matches("\\d{1,2}:\\d{2}\\s?(am|pm)");
    }

    private static LocalTime parse12HourWithColon(String timeString) {
        return LocalTime.parse(timeString, FORMAT_12_HOUR);
    }

    private static boolean is12HourWithoutColonAmPm(String timeString) {
        return timeString.matches("\\d{3}(am|pm)");
    }

    private static LocalTime parse12HourWithoutColon(String timeString) {
        String formattedTime = timeString.substring(0, 1) + ":" + timeString.substring(1, 3) + timeString.substring(3);
        return LocalTime.parse(formattedTime, FORMAT_12_HOUR);
    }

    private static boolean is24HourWithoutColon(String timeString) {
        return timeString.matches("\\d{4}");
    }

    private static LocalTime parse24HourWithoutColon(String timeString) {
        return LocalTime.parse(timeString, FORMAT_24_HOUR);
    }

    private static boolean is24HourWithColon(String timeString) {
        return timeString.matches("\\d{2}:\\d{2}");
    }

    private static LocalTime parse24HourWithColon(String timeString) {
        return LocalTime.parse(timeString, FORMAT_24_HOUR_COLON);
    }

    // New helper for 24-hour single-digit hour with colon (e.g., 8:30)
    private static boolean is24HourSingleDigitWithColon(String timeString) {
        return timeString.matches("\\d{1}:\\d{2}");
    }
    private static LocalTime parse24HourSingleDigitWithColon(String timeString) {
        timeString = "0" + timeString; // Add leading zero (e.g., "8:30" -> "08:30")
        return LocalTime.parse(timeString, FORMAT_24_HOUR_COLON);
    }
    // Update for 12-hour format without colon and PM (e.g., 1115pm)
    private static boolean is12HourWithoutColonFourDigitAmPm(String timeString) {
        return timeString.matches("\\d{4}(am|pm)");
    }

    private static LocalTime parse12HourWithoutColonFourDigit(String timeString) {
        String formattedTime = timeString.substring(0, 2) + ":" + timeString.substring(2, 4) + timeString.substring(4);
        return LocalTime.parse(formattedTime, FORMAT_12_HOUR);
    }

}
