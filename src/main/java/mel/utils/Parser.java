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
        "d-M-[uu][uuuu] HHmm",
        "d-M-[uu][uuuu] HH:mm",
        "d-M-[uu][uuuu] h.mma",
        "d-M-[uu][uuuu] hmma",
        "d-M-[uu][uuuu] ha",
        "d/M/[uu][uuuu] HHmm",
        "d/M/[uu][uuuu] HH:mm",
        "d/M/[uu][uuuu] h.mma",
        "d/M/[uu][uuuu] hmma",
        "d/M/[uu][uuuu] ha",
        "[uu][uuuu]-M-d HHmm",
        "[uu][uuuu]-M-d HH:mm",
        "[uu][uuuu]-M-d h.mma",
        "[uu][uuuu]-M-d hmma",
        "[uu][uuuu]-M-d ha",
        "[uu][uuuu]/M/d HHmm",
        "[uu][uuuu]/M/d HH:mm",
        "[uu][uuuu]/M/d h.mma",
        "[uu][uuuu]/M/d hmma",
        "[uu][uuuu]/M/d ha",
    };

    /**
     * Predetermined formats of date inputs,
     * for parsing with DateTimeFormatter formatter.
     */
    private static final String[] FORMAT_DATE = {
        "d-M-[uu][uuuu]",
        "d/M/[uu][uuuu]",
        "[uu][uuuu]/M/d",
        "[uu][uuuu]-M-d",
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
