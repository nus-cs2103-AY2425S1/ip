package xbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testEmpty() {
        assertEquals(false, Parser.isValidDateFormat(""));
    }

    @Test
    public void testDateWithZero() {
        assertEquals(true, Parser.isValidDateFormat("05/02/2024"));
    }

    @Test
    public void testDateWithoutZero() {
        assertEquals(true, Parser.isValidDateFormat("5/2/2024"));
    }

    @Test
    public void testDateWithShortYear() {
        assertEquals(false, Parser.isValidDateFormat("5/2/24"));
    }

    @Test
    public void testValidDateTime() {
        assertEquals(true, Parser.isValidDateFormat("5/2/2024 0924"));
    }

    @Test
    public void testTimeAfter24Hours() {
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 2450"));
    }

    @Test
    public void testTimeAfter60Minutes() {
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 1060"));
    }

    @Test
    public void testTimeOfDifferentFormat() {
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 6pm"));
    }

    @Test
    public void testDateOfDifferentFormat() {
        assertEquals(false, Parser.isValidDateFormat("2024-05-06"));
    }
}
