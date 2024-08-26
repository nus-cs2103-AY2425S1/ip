package chatbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void encode_unmarked() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2020-10-10 10:10", formatter);
        LocalDateTime to = LocalDateTime.parse("2020-10-10 11:11", formatter);
        Event event = new Event("test", from, to);
        assertEquals(event.encode(),"E|0|test|2020-10-10T10:10|2020-10-10T11:11");
    }

    @Test
    public void encode_marked() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2020-10-10 10:10", formatter);
        LocalDateTime to = LocalDateTime.parse("2020-10-10 11:11", formatter);
        Event event = new Event("test", from, to, true);
        assertEquals(event.encode(),"E|1|test|2020-10-10T10:10|2020-10-10T11:11");
    }
}
