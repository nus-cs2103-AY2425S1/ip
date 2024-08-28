package delta.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testEquivalentConstructors() {
        try {
            Deadline deadline1 = new Deadline("test", "Aug 28 2024 4PM");
            Deadline deadline2 = new Deadline("test",
                    LocalDateTime.parse("2024-08-28 1600", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            assertEquals(deadline1.toString(), deadline2.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
