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
                "[D][x] testing event is marked (by: 2024-08-29 2303)");
    }

    @Test
    public void testEventMarkAsIncomplete() {
        Task event = new Event("testing event is unmarked",
                true, LocalDateTime.parse("2024-08-29 2303", dateTimeFormat),
                LocalDateTime.parse("2024-08-29 2333", dateTimeFormat));
        event.mark();
        assertEquals(event.toString(),
                "[D][ ] testing event is unmarked (by: 2024-08-29 2303)");
    }
}
