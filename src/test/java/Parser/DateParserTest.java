package Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateParserTest {
    private DateParser d = new DateParser();

    @Test
    public void parseAndFormatDateTime() {

        assertEquals("1st january 2023, 12:00am", d.parseAndFormatDateTime("1/1/2023 0000"));

        assertEquals("1st january 2023", d.parseAndFormatDateTime("1/1/2023"));

        assertEquals("1st january 2024, 12:00am", d.parseAndFormatDateTime("2024-01-01 0000"));

        assertEquals("1st december 2024", d.parseAndFormatDateTime("2024-12-01"));

        assertEquals("1st november 2024, 12:00am", d.parseAndFormatDateTime("01/11/2024 0000"));
    }
}
