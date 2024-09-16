package elysia.dateparser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import elysia.exception.InvalidDateFormatException;
import elysia.parser.DateParser;

/**
 * Unit tests for the DateParser class. Tests various scenarios for parsing dates.
 */
public class DateParserTest {

    /**
     * Tests parsing of a weekday abbreviation. Verifies that the parser correctly converts the weekday abbreviation
     * "Mon" to the corresponding value.
     *
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Test
    public void parseDate_weekday_success() throws InvalidDateFormatException {
        assertEquals(DateParser.buildMap().get("mon"), DateParser.parseDate("Mon"));
    }

    /**
     * Tests parsing of a date without a year. Verifies that the parser correctly converts the date string "2nd Sep" to
     * the corresponding LocalDate.
     *
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Test
    public void parseDate_dateWithoutYear_success() throws InvalidDateFormatException {
        assertEquals(LocalDate.of(2024, 9, 2), DateParser.parseDate("2nd Sep"));
    }

    /**
     * Tests parsing of a date with a year. Verifies that the parser correctly converts the date string "2nd Sep 2024"
     * to the corresponding LocalDate.
     *
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Test
    public void parseDate_dateWithYear_success() throws InvalidDateFormatException {
        assertEquals(LocalDate.of(2024, 9, 2), DateParser.parseDate("2nd Sep 2024"));
    }
}
