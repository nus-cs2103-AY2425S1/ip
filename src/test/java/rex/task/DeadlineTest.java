package rex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testFormattedNotDone() {
        assertEquals("D | 0 | do this | 01-09-24 1200",
                new Deadline("do this", false, LocalDateTime.of(2024, 9, 1, 12, 0)).formatter());
    }

    @Test
    public void testFormattedDone() {
        assertEquals("D | 1 | do that thing | 01-09-24 1200",
                new Deadline("do that thing", true, LocalDateTime.of(2024, 9, 1, 12, 0)).formatter());
    }

    @Test
    public void testToStringNotDone() {
        assertEquals("[D][ ] submit assignment(by: 01 Sep 2024 1200HRS)",
                new Deadline("submit assignment", false, LocalDateTime.of(2024, 9, 1, 12, 0)).toString());
    }

    @Test
    public void testToStringDone() {
        assertEquals("[D][X] finish homework(by: 01 Sep 2024 1200HRS)",
                new Deadline("finish homework", true, LocalDateTime.of(2024, 9, 1, 12, 0)).toString());
    }
}
