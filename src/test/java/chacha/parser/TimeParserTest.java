package chacha.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimeParserTest {

    @Test
    public void testParseStringToTime_expectedOutcome() {
        assertEquals(LocalTime.of(5, 0), TimeParser.parseStringToTime("5am"));
        assertEquals(LocalTime.of(5, 30), TimeParser.parseStringToTime("5.30am"));
        assertEquals(LocalTime.of(12, 0), TimeParser.parseStringToTime("12pm"));
        assertEquals(LocalTime.of(12, 45), TimeParser.parseStringToTime("12.45pm"));
    }

    @Test
    public void testParseStringToTime_exceptionThrown() {
        assertNull(TimeParser.parseStringToTime("invalid time"));
        assertNull(TimeParser.parseStringToTime("700am"));
        assertNull(TimeParser.parseStringToTime("5:00am"));
        assertNull(TimeParser.parseStringToTime("5:00m"));
    }

    @Test
    public void testParseTimeToString_expectedOutcome() {
        // Test valid LocalTime objects
        assertEquals("5.00am", TimeParser.parseTimeToString(LocalTime.of(5, 0)));
        assertEquals("5.30am", TimeParser.parseTimeToString(LocalTime.of(5, 30)));
        assertEquals("12.00pm", TimeParser.parseTimeToString(LocalTime.of(12, 0)));
        assertEquals("12.43pm", TimeParser.parseTimeToString(LocalTime.of(12, 43)));
    }
}
