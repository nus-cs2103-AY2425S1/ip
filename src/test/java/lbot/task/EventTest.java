package lbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void testEventMarkAsComplete() {
        Task event = new Event("testing event is marked",
                false, LocalDateTime.parse("2024-08-29 2303", dateTimeFormat),
                LocalDateTime.parse("2024-08-29 2333", dateTimeFormat));
        event.mark();
        assertEquals(event.toString(),
                "[E][x] testing event is marked (from: 2024-08-29 2303 to: 2024-08-29 2333)");
    }

    @Test
    public void testEventMarkAsIncomplete() {
        Task event = new Event("testing event is unmarked",
                true, LocalDateTime.parse("2024-08-29 2303", dateTimeFormat),
                LocalDateTime.parse("2024-08-29 2333", dateTimeFormat));
        event.mark();
        assertEquals(event.toString(),
                "[E][ ] testing event is unmarked (from: 2024-08-29 2303 to: 2024-08-29 2333)");
    }
}
