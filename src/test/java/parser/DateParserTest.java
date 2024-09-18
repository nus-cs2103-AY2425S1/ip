package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * test class to test the Date Parser class
 */
public class DateParserTest {
    private DateParser d = new DateParser();

    /**
     * Tests the {@code parseAndFormatDateTime} method of the {@code d} object with various date and time formats.
     * This test ensures that the method correctly parses and formats date and time strings into a more readable format.
     *
     * <p>Expected formats for output:
     * <ul>
     *   <li>Date with time: "1st january 2023, 12:00am"</li>
     *   <li>Date without time: "1st january 2023"</li>
     * </ul>
     *
     * <p>The method is tested against several date formats including:
     * <ul>
     *   <li>"1/1/2023 0000" -> "1st january 2023, 12:00am"</li>
     *   <li>"1/1/2023" -> "1st january 2023"</li>
     *   <li>"2024-01-01 0000" -> "1st january 2024, 12:00am"</li>
     *   <li>"2024-12-01" -> "1st december 2024"</li>
     *   <li>"01/11/2024 0000" -> "1st november 2024, 12:00am"</li>
     * </ul>
     *
     * <p>It checks if the parsed date and time match the expected formatted strings.
     */
    @Test
    public void parseAndFormatDateTime() {

        assertEquals("1st january 2023, 12:00am", d.parseAndFormatDateTime("1/1/2023 0000"));

        assertEquals("1st january 2023", d.parseAndFormatDateTime("1/1/2023"));

        assertEquals("1st january 2024, 12:00am", d.parseAndFormatDateTime("2024-01-01 0000"));

        assertEquals("1st december 2024", d.parseAndFormatDateTime("2024-12-01"));

        assertEquals("1st november 2024, 12:00am", d.parseAndFormatDateTime("01/11/2024 0000"));
    }
}
