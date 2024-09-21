package meep.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import meep.task.Storage;


class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser(new Storage("data.txt")); // Assuming Storage can be instantiated without parameters
    }

    @Test
    void formatDate_validInput_correctlyFormatted() {
        String input = "15/3/2024 1430";
        String expected = "15th of March 2024, 2:30pm";
        assertEquals(expected, parser.formatDate(input));
    }

    @Test
    void formatDate_validInputWithSingleDigitDay_correctlyFormatted() {
        String input = "5/12/2024 0900";
        String expected = "5th of December 2024, 9:00am";
        assertEquals(expected, parser.formatDate(input));
    }

    @Test
    void formatDate_validInputWithMidnight_correctlyFormatted() {
        String input = "1/1/2025 0000";
        String expected = "1st of January 2025, 12:00am";
        assertEquals(expected, parser.formatDate(input));
    }

    @Test
    void formatDate_invalidDate_throwsException() {
        String input = "31/2/2024 1200"; // Invalid date (February 31st)
        assertThrows(DateTimeParseException.class, () -> parser.formatDate(input));
    }

    @Test
    void formatDate_invalidFormat_throwsException() {
        String input = "15-03-2024 14:30"; // Invalid format
        assertThrows(DateTimeParseException.class, () -> parser.formatDate(input));
    }

    @Test
    void formatDate_emptyString_throwsException() {
        String input = "";
        assertThrows(DateTimeParseException.class, () -> parser.formatDate(input));
    }

    @Test
    void getDayOfMonthSuffix_variousDays_correctSuffix() {
        // We need to test the private method indirectly through formatDate
        assertEquals("1st of January 2024, 12:00pm", parser.formatDate("1/1/2024 1200"));
        assertEquals("2nd of February 2024, 12:00pm", parser.formatDate("2/2/2024 1200"));
        assertEquals("3rd of March 2024, 12:00pm", parser.formatDate("3/3/2024 1200"));
        assertEquals("4th of April 2024, 12:00pm", parser.formatDate("4/4/2024 1200"));
        assertEquals("11th of May 2024, 12:00pm", parser.formatDate("11/5/2024 1200"));
        assertEquals("21st of June 2024, 12:00pm", parser.formatDate("21/6/2024 1200"));
        assertEquals("22nd of July 2024, 12:00pm", parser.formatDate("22/7/2024 1200"));
        assertEquals("23rd of August 2024, 12:00pm", parser.formatDate("23/8/2024 1200"));
    }
}
