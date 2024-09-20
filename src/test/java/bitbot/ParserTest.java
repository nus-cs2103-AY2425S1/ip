package bitbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/*
I used GPT to help me with the generation of test cases.
 */

/**
 * Unit test for Parser class.
 */
public class ParserTest {
    @Test
    public void testExtractDescription_success() {
        String[] input = {"event", "return", "book", "/from", "Mon", "4pm"};
        String result = Parser.extractDescription(input, "/from");
        assertEquals("return book", result);
    }

    @Test
    public void testExtractFromTimeDetail_success() {
        String[] input = {"event", "return", "book", "/from", "16-09-2024", "18:00", "/to", "17-09-2024", "18:30"};
        String result = Parser.extractTimeDetail(input, "/from", "/to");
        assertEquals("16-09-2024 18:00", result);
    }

    @Test
    public void testExtractTimeDetail_noToKeyword_success() {
        String[] input = {"event", "meeting", "/from", "2pm"};
        String result = Parser.extractTimeDetail(input, "/from", null);
        assertEquals("2pm", result);
    }

    @Test
    public void testExtractToTimeDetail_success() {
        String[] input = {"event", "return", "book", "/from", "16-09-2024", "18:00", "/to", "17-09-2024", "18:30"};
        String result = Parser.extractTimeDetail(input, "/to", null);
        assertEquals("17-09-2024 18:30", result);
    }
    @Test
    public void testParseDateTime_invalid() {
        LocalDateTime dateTime = Parser.parseDateTime("invalid-date");
        assertNull(dateTime);
    }

}
