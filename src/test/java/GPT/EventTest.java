package GPT;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testToFileFormat_withValidDate() {
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        String expectedFormat = "E | 0 | Team meeting | 2023-08-26 1000 | 2023-08-26 1200";
        assertEquals(expectedFormat, event.toFileFormat());
    }

    @Test
    public void testToFileFormat_withInvalidDate() {
        Event event = new Event("Team meeting", null, null);

        String expectedFormat = "E | 0 | Team meeting | [Invalid Date] | [Invalid Date]";
        assertEquals(expectedFormat, event.toFileFormat());
    }

    @Test
    public void testEventToString_withValidDate() {
        LocalDateTime fromDateTime = LocalDateTime.of(2023, 8, 26, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, 8, 26, 12, 0);
        Event event = new Event("Team meeting", fromDateTime, toDateTime);

        String expectedString = "[E][ ] Team meeting (from: Aug 26 2023, 10:00AM to: Aug 26 2023, 12:00PM)";
        // Normalize both strings to lowercase for comparison
        assertEquals(expectedString.toLowerCase(), event.toString().toLowerCase());
    }

    @Test
    public void testEventToString_withInvalidDate() {
        Event event = new Event("Team meeting", null, null);

        String expectedString = "[E][ ] Team meeting (from: [Invalid Date] to: [Invalid Date])";
        // No need to normalize here as there is no AM/PM in this string
        assertEquals(expectedString, event.toString());
    }
}
