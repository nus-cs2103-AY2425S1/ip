package monique.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import monique.exception.IllegalDateFormatException;

/**
 * The <code>DateParser</code> class provides functionality to parse and interpret date and time
 * strings into {@link LocalDateTime} objects. It supports various date formats and can handle
 * specific cases such as "tomorrow" with optional times.
 */
public class DateParser {
    public static final String DATE_PATTERN = "\\d{1,2}[/\\-]\\d{1,2}[/\\-]\\d{4}( \\d{4})?";
    public static final String DAY_PATTERN = "(?i)(mon|tue|wed|thu|fri|sat|sun|monday|tuesday|wednesday|"
                                             + "thursday|friday|saturday|sunday)( \\d{4})?( \\d{1,2}(am|pm))?";

    public static final int WEEK_OFFSET = 7;

    /**
     * Enum for supported date formats.
     */
    public enum DateFormatType {
        TYPE1_SLASH("M/d/yyyy[ HHmm]"),
        TYPE1_DASH("M-d-yyyy[ HHmm]");

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

    /**
     * Parses the given date string into a {@link LocalDateTime} object.
     * Supports specific cases such as "tomorrow" and various date formats.
     *
     * @param originalString The date string to parse.
     * @return The parsed {@link LocalDateTime} object.
     * @throws IllegalDateFormatException If the date string does not match any known format.
     */
    public static LocalDateTime getDateTimeString(String originalString) throws IllegalDateFormatException {
        try {
            // Check for the special "tomorrow" case with optional time
            if (originalString.toLowerCase().startsWith("tomorrow")) {
                String timePart = originalString.substring("tomorrow".length()).trim();
                LocalDate tomorrowDate = LocalDate.now().plusDays(1);
                if (!timePart.isEmpty()) {
                    return parseTime(tomorrowDate, timePart);
                }
                return tomorrowDate.atStartOfDay();
            }

            if (originalString.matches(DATE_PATTERN)) {
                return parseType1(originalString);
            } else if (originalString.matches(DAY_PATTERN)) {
                return parseType2(originalString);
            } else {
                throw new IllegalDateFormatException();
            }
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException();
        }
    }

    private static LocalDateTime parseType1(String originalString) throws IllegalDateFormatException {
        DateFormatType dateFormatType = DateFormatType.getType(originalString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatType.getPattern());

        // Parse date using the determined pattern and extract the LocalDateTime
        return LocalDateTime.parse(originalString, formatter);
    }

    private static LocalDateTime parseType2(String originalString) throws IllegalDateFormatException {
        String[] parts = originalString.toLowerCase().split(" ");
        DayOfWeekEnum dayEnum = DayOfWeekEnum.fromString(parts[0]);

        LocalDate nearestDate = getNextDayOfWeek(dayEnum.getDayOfWeek());

        if (parts.length > 1) {
            String timePart = parts[1];
            if (parts.length > 2) {
                timePart += " " + parts[2]; // Handle time in AM/PM format
            }
            return parseTime(nearestDate, timePart);
        }

        return nearestDate.atStartOfDay();
    }

    private static LocalDate getNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDate today = LocalDate.now();
        int daysUntilNext = dayOfWeek.getValue() - today.getDayOfWeek().getValue();
        if (daysUntilNext < 0) {
            daysUntilNext += WEEK_OFFSET;
        }
        return today.plusDays(daysUntilNext);
    }

    private static LocalDateTime parseTime(LocalDate date, String timeString) throws IllegalDateFormatException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h[mm]a");
        LocalDateTime dateTime;
        try {
            LocalTime localTime = LocalTime.parse(timeString.toLowerCase(), timeFormatter);
            dateTime = date.atTime(localTime);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException();
        }
        return dateTime;
    }
}
