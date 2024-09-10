package mel.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mel.exceptions.ParseException;

/**
 * Parser class that parses a regular expression of date and/or time
 * of valid format to a LocalDateTime object representation.
 */
public class Parser {
    /**
     * Predetermined formats of date-time inputs,
     * for parsing with DateTimeFormatter formatter.
     */
    private static final String[] FORMAT_DATE_TIME = {
        "d-M-[uuuu][uu] HHmm",
        "d-M-[uuuu][uu] HH:mm",
        "d-M-[uuuu][uu] h.mma",
        "d-M-[uuuu][uu] hmma",
        "d-M-[uuuu][uu] ha",
        "d/M/[uuuu][uu] HHmm",
        "d/M/[uuuu][uu] HH:mm",
        "d/M/[uuuu][uu] h.mma",
        "d/M/[uuuu][uu] hmma",
        "d/M/[uuuu][uu] ha",
        "[uuuu][uu]-M-d HHmm",
        "[uuuu][uu]-M-d HH:mm",
        "[uuuu][uu]-M-d h.mma",
        "[uuuu][uu]-M-d hmma",
        "[uuuu][uu]-M-d ha",
        "[uuuu][uu]/M/d HHmm",
        "[uuuu][uu]/M/d HH:mm",
        "[uuuu][uu]/M/d h.mma",
        "[uuuu][uu]/M/d hmma",
        "[uuuu][uu]/M/d ha",
    };

    /**
     * Predetermined formats of date inputs,
     * for parsing with DateTimeFormatter formatter.
     */
    private static final String[] FORMAT_DATE = {
        "d-M-[uuuu][uu]",
        "d/M/[uuuu][uu]",
        "[uuuu][uu]/M/d",
        "[uuuu][uu]-M-d",
    };

    /**
     * Returns a LocalDateTime representing date-time, by parsing
     * a date and/or time input of valid format.
     * @param str date and/or time input string.
     * @return parsed date-time.
     * @throws ParseException if date-time input cannot be parsed
     *      or is of invalid format.
     * @see LocalDateTime
     */
    public LocalDateTime parseDateTime(String str) throws ParseException {
        assert !str.isEmpty() : "date/time field should not be empty";
        for (String s : FORMAT_DATE) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(s))
                        .atStartOfDay();
            } catch (DateTimeParseException e) {
                /*
                 * Fallthrough: DateTimeParseException is ignored to test
                 * all variations of date formats.
                 */
            }
        }
        for (String s : FORMAT_DATE_TIME) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(s));
            } catch (DateTimeParseException e) {
                /*
                 * Fallthrough: DateTimeParseException is ignored to test
                 * all variations of date/time formats.
                 */
            }
        }
        throw new ParseException(str);
    }
}
