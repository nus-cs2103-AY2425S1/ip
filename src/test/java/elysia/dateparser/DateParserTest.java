package elysia.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void dateParser_parseDate_weekday_success(){
        assertEquals(DateParser.buildMap().get("mon"), DateParser.parseDate("Mon"));
    }

    @Test
    public void dateParser_parseDate_dateWithoutYear_success(){
        assertEquals(LocalDate.of(2024,9,2), DateParser.parseDate("2nd Sep"));
    }

    @Test
    public void dateParser_parseDate_dateWithYear_success(){
        assertEquals(LocalDate.of(2024,9,2), DateParser.parseDate("2nd Sep 2024"));
    }

}