package elysia.dateparser;

import elysia.exception.InvalidDateFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void dateParser_parseDate_weekday_success() throws InvalidDateFormatException {
        assertEquals(DateParser.buildMap().get("mon"), DateParser.parseDate("Mon"));
    }

    @Test
    public void dateParser_parseDate_dateWithoutYear_success() throws InvalidDateFormatException {
        assertEquals(LocalDate.of(2024, 9, 2), DateParser.parseDate("2nd Sep"));
    }

    @Test
    public void dateParser_parseDate_dateWithYear_success() throws InvalidDateFormatException {
        assertEquals(LocalDate.of(2024, 9, 2), DateParser.parseDate("2nd Sep 2024"));
    }

}