package task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testParseDateTimeWithFullDateTime() {
        Event event = new Event();
        String input = "10/9/2024 3pm";
        LocalDateTime expected = LocalDateTime.parse("2024-09-10 1500",DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime actual = event.parseDateTime(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testParseDateTimeWithOnlyDate() {
        Event event = new Event();
        String input = "10/9/2024";
        LocalDateTime expected = LocalDateTime.parse("2024-09-10T00:00",DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime actual = event.parseDateTime(input);
        assertEquals(expected, actual);
    }
}
