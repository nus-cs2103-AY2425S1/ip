package chatbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void encode_unmarked() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline deadline = new Deadline("test", LocalDateTime.parse("2020-10-10 10:10", formatter));
        assertEquals(deadline.encode(),"D|0|test|2020-10-10T10:10");
    }

    @Test
    public void encode_marked() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline deadline = new Deadline("test", LocalDateTime.parse("2020-10-10 10:10", formatter), true);
        assertEquals(deadline.encode(),"D|1|test|2020-10-10T10:10");
    }
}
