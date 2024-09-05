package elara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.parse("2024-08-06 1300", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assertEquals("[D][ ] return book (by: Aug 6 2024 01:00 pm)",
                new DeadlineTask("return book", deadline, false).toString());
    }
}
