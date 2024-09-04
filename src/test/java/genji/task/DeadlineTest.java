package genji.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

/**
 * A test class to test deadline tasks
 */
public class DeadlineTest {

    /**
     * Tests if the toString method works properly
     * Creating a deadline object and compare with expected output
     */
    @Test
    public void testToString() {
        LocalDateTime l = LocalDateTime.of(2024, 9, 3, 11, 0);
        assertEquals("[D][ ] Test (by: Sep 03 2024 11:00)",
                new Deadline("Test", l).toString());
    }

    /**
     * Tests if the toListString method works properly
     * Creating a deadline object and compare with expected output
     */
    @Test
    public void testToListString() {
        LocalDateTime l = LocalDateTime.of(2024, 9, 3, 11, 0);
        assertEquals("D | 0 | Test | Sep 03 2024 11:00",
                new Deadline("Test", l).toListString());
    }
}
