package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

/**
 * Enum {@code DateTimeParser} provides a set of predefined date-time formats for parsing strings
 * into {@link LocalDateTime} or {@link LocalDate} objects. It supports multiple date formats and
 * encapsulates the parsing logic within the enum constants.
 * <p>
 * Each constant is associated with a specific date-time format pattern and its corresponding
 * {@link DateTimeFormatter}. The {@code parse} method tries each defined format to parse
 * a given date string, returning the result wrapped in an {@link Optional}.
 * </p>
 */
public enum DateTimeFormatEnum {

    /**
     * Date-time format with a time component in 24-hour format (e.g., "2/12/2019 1800").
     */
    DATE_TIME_WITH_TIME("d/M/yyyy HHmm", DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),

    /**
     * Date-time format with a time component including AM/PM meridiem (e.g., "Dec 2 2019, 6:00 PM").
     */
    DATE_TIME_FOR_SAVING("MMM d yyyy, h:mm a", DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a",
            new Locale("en", "SG"))),

    /**
     * Date-only format without a time component (e.g., "2/12/2019").
     * Default time is set to the start of the day (00:00).
     */
    DATE_ONLY("d/M/yyyy", DateTimeFormatter.ofPattern("d/M/yyyy"));

    private final String pattern;
    private final DateTimeFormatter formatter;

    /**
     * Constructs a {@code DateTimeParser} enum constant with the specified date format pattern and formatter.
     *
     * @param pattern   the date format pattern
     * @param formatter the {@link DateTimeFormatter} corresponding to the pattern
     */
    DateTimeFormatEnum(String pattern, DateTimeFormatter formatter) {
        this.pattern = pattern;
        this.formatter = formatter;
    }

    /**
     * Returns the format pattern as a string.
     *
     * @return the format pattern string
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Returns the {@link DateTimeFormatter} associated with this enum constant.
     *
     * @return the formatter for this date format
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Attempts to parse the given date string using the predefined date-time formats.
     * <p>
     * This method iterates over the available enum constants, attempting to match the input string
     * with each corresponding format. If the format is {@code DATE_ONLY}, the resulting date will
     * default to midnight (00:00) for the time component. The method returns the parsed date wrapped
     * in an {@link Optional}, or an empty {@code Optional} if no valid format is found.
     * </p>
     *
     * @param dateTimeStr the date-time string to be parsed
     * @return an {@link Optional} containing the parsed {@link LocalDateTime},or an empty {@code Optional}.
     */
    public static Optional<LocalDateTime> parse(String dateTimeStr) {
        for (DateTimeFormatEnum format : values()) {
            try {
                if (format == DATE_ONLY) {
                    LocalDate date = LocalDate.parse(dateTimeStr, format.getFormatter());
                    return Optional.of(date.atStartOfDay()); // Set time to midnight if only date is provided
                } else {
                    return Optional.of(LocalDateTime.parse(dateTimeStr, format.getFormatter()));
                }
            } catch (DateTimeParseException e) {
                // If parsing fails, continue trying the next format
            }
        }
        return Optional.empty();
    }
}
