package genji.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime l = LocalDateTime.of(2024, 9, 3, 11, 0);
        assertEquals("[D][ ] Test (by: Sep 03 2024 11:00)",
                new Deadline("Test", l).toString());
    }

    @Test
    public void testToListString() {
        LocalDateTime l = LocalDateTime.of(2024, 9, 3, 11, 0);
        assertEquals("D | 0 | Test | Sep 03 2024 11:00",
                new Deadline("Test", l).toListString());
    }
}
