package torne.util;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This encapsulates a date-time object to be used by Torne.
 */
public class TorneDateTime {
    private final LocalDateTime localDateTime;

    TorneDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Creates a {@link TorneDateTime} from date time input string of the format `yyyy-MM-dd HHmm`,
     * for instance
     * 2024-09-30 1820, or 1970-01-01 0000.
     *
     * @param input Input string.
     * @return `torne.util.TorneDateTime` object with the given date and time.
     * @throws TorneInvalidCommandException if the string cannot be parsed.
     */
    public static TorneDateTime parseInputDateTimeString(String input) throws TorneInvalidCommandException {
        assert input != null : "input should not be null";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, dtf);
            return new TorneDateTime(parsedDateTime);
        } catch (DateTimeParseException e) {
            throw new TorneInvalidCommandException(
                    "Unable to parse given date time string: " + input + " : " + e.getLocalizedMessage());
        }

    }

    /**
     * Creates a {@link TorneDateTime} from date input string of the format `yyyy-MM-dd`,
     * for instance
     * 2024-09-30, or 1970-01-01.
     *
     * @param input Input string.
     * @return `torne.util.TorneDateTime` object with the given date and time.
     * @throws TorneInvalidCommandException if the string cannot be parsed.
     */
    public static TorneDateTime parseInputDateString(String input) throws TorneInvalidCommandException {
        assert input != null : "input should not be null";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, dtf);
            return new TorneDateTime(parsedDateTime);
        } catch (DateTimeParseException e) {
            throw new TorneInvalidCommandException(
                    "Unable to parse given date string: " + input + " : " + e.getLocalizedMessage());
        }
    }

    /**
     * Creates a {@link TorneDateTime} from datetime storage string as seconds since epoch
     * (1970-01-01T00:00:00Z).
     *
     * @param input Input string.
     * @return `torne.util.TorneDateTime` object with the given date and time.
     * @throws TorneInvalidDataException if the string cannot be parsed.
     */
    public static TorneDateTime parseStorageString(String input) throws TorneInvalidDataException {
        assert input != null : "input should not be null";
        try {
            long timeInSeconds = Long.parseLong(input);
            LocalDateTime parsedDateTime = LocalDateTime.ofEpochSecond(timeInSeconds, 0, ZoneOffset.UTC);
            return new TorneDateTime(parsedDateTime);
        } catch (DateTimeParseException e) {
            throw new TorneInvalidDataException(
                    "Unable to parse loaded datetime string: " + input + " : " + e.getLocalizedMessage());
        }
    }

    /**
     * Creates a {@link TorneDateTime} from datetime storage string of the ISO-8601 format.
     * Used when loading date time strings from local storage
     *
     * @param input Input string.
     * @return `torne.util.TorneDateTime` object with the given date and time.
     * @throws TorneInvalidDataException if the string cannot be parsed.
     */
    public static TorneDateTime parseIsoString(String input) throws TorneInvalidDataException {
        assert input != null : "input should not be null";
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, DateTimeFormatter.ISO_DATE_TIME);
            return new TorneDateTime(parsedDateTime);
        } catch (DateTimeParseException e) {
            throw new TorneInvalidDataException(
                    "Unable to parse loaded datetime string: " + input + " : " + e.getLocalizedMessage());
        }
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Returns a string used to store the date time in a local file.
     * That string is the number of seconds passed since epoch (1970-01-01T00:00:00Z).
     *
     * @return String representation of date time used for local storage.
     */
    public String toStorageString() {
        long timeInSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        return String.format("%d", timeInSeconds);
    }

    public String toIsoString() {
        // ISO 8601 is best format
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return localDateTime.format(dtf);
    }

    /**
     * Returns a string describing the date/time relative to the time now.
     *
     * @return String representation of relative date/time.
     */
    public String toRelativeString() {
        LocalDateTime now = LocalDateTime.now();
        boolean isFuture = localDateTime.isAfter(now); // so equal is considered "past"

        // make sure that diff is positive
        Duration diff  = isFuture ? Duration.between(now, localDateTime) : Duration.between(localDateTime, now);
        String differenceString;

        if (diff.toSeconds() < 60) {
            return isFuture ? "in less than a minute" : "just now";
        } else if (diff.toMinutes() < 60) {
            long mins = diff.toMinutes();
            differenceString = String.format("%d minute%s", mins, mins > 1 ? "s" : "");
        } else if (diff.toHours() < 24) {
            long hrs = diff.toHours();
            differenceString = String.format("%d hour%s", hrs, hrs > 1 ? "s" : "");
        } else if (diff.toDays() < 7) {
            long days = diff.toDays();

            if (days == 1) {
                return isFuture ? "tomorrow" : "yesterday";
            }

            differenceString = String.format("%d day%s", days, days > 1 ? "s" : "");
        } else if (diff.toDays() < 30) {
            long weeks = (diff.toDays() / 7);
            differenceString = String.format("%d week%s", weeks, weeks > 1 ? "s" : "");
        } else if (diff.toDays() < 365) {
            long months = (diff.toDays() / 30);
            differenceString = String.format("%d month%s", months, months > 1 ? "s" : "");
        } else {
            long years = (diff.toDays() / 365);
            differenceString = String.format("%d year%s", years, years > 1 ? "s" : "");
        }

        return isFuture ? "in " + differenceString : differenceString + " ago";
    }
}
