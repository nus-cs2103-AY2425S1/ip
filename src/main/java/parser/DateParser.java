package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Provides the Date Parsing functionality that allows ui.Delphi to process dates and times in different formats.
 * Written with the help of chatgpt
 * @author Jordan Chan
 */
public class DateParser {
    /**
     * List of possible date-time formats.
     */
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/MM/yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH),
    };

    /**
     * Parses a date and time string or a date-only string and converts it to a formatted string using default output format.
     *
     * @param dateTimeString The date and time or date-only string to parse.
     * @return A formatted date and time string, or null if parsing fails.
     */
    public String parseAndFormatDateTime(String dateTimeString) {
        return parseAndFormatDateTime(dateTimeString, null);
    }

    /**
     * Parses a date and time string or a date-only string and converts it to a formatted string using the specified output format.
     *
     * @param dateTimeString The date and time or date-only string to parse.
     * @param outputPattern  The output format pattern.
     * @return A formatted date and time string, or null if parsing fails.
     */
    public String parseAndFormatDateTime(String dateTimeString, String outputPattern) {
        DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma", Locale.ENGLISH);
        DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);

        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                return formatDateTime(dateTime, outputPattern);
            } catch (DateTimeParseException e) {
                // Continue to date-only parsing
            }
        }

        try {
            LocalDate dateOnly = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
            return formatDateOnly(dateOnly, outputPattern);
        } catch (DateTimeParseException e) {
            try {
                LocalDate dateOnly = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH));
                return formatDateOnly(dateOnly, outputPattern);
            } catch (DateTimeParseException ex) {
                // Return original string if parsing fails
            }
        }

        return dateTimeString;
    }

    private String formatDateTime(LocalDateTime dateTime, String outputPattern) {
        DateTimeFormatter outputFormatter = getOutputFormatter(outputPattern, true);
        String formattedDateTime = dateTime.format(outputFormatter);
        int day = dateTime.getDayOfMonth();
        String ordinalDay = day + OrdinalSuffix.getSuffix(day);
        return formattedDateTime.replaceFirst("\\d+", ordinalDay).toLowerCase();
    }

    private String formatDateOnly(LocalDate date, String outputPattern) {
        DateTimeFormatter outputFormatter = getOutputFormatter(outputPattern, false);
        String formattedDateOnly = date.format(outputFormatter);
        int day = date.getDayOfMonth();
        String ordinalDay = day + OrdinalSuffix.getSuffix(day);
        return formattedDateOnly.replaceFirst("\\d+", ordinalDay).toLowerCase();
    }

    private DateTimeFormatter getOutputFormatter(String outputPattern, boolean hasTime) {
        if (outputPattern == null || outputPattern.isEmpty()) {
            return hasTime
                    ? DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma", Locale.ENGLISH)
                    : DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
        }
        return DateTimeFormatter.ofPattern(outputPattern, Locale.ENGLISH);
    }

    /**
     * Enum representing ordinal suffixes for dates.
     */
    public enum OrdinalSuffix {
        ST("st"), ND("nd"), RD("rd"), TH("th");

        private final String suffix;

        OrdinalSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getSuffix() {
            return suffix;
        }

        /**
         * Returns the ordinal suffix for a given day of the month.
         *
         * @param day The day of the month.
         * @return The ordinal suffix ("st", "nd", "rd", "th").
         */
        public static String getSuffix(int day) {
            if (day >= 11 && day <= 13) {
                return TH.getSuffix();
            }
            switch (day % 10) {
            case 1: return ST.getSuffix();
            case 2: return ND.getSuffix();
            case 3: return RD.getSuffix();
            default: return TH.getSuffix();
            }
        }
    }
}
