package johncena.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the string conversion of a Deadline object.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] study (by: 2024-09-23 1100)",
                new Deadline("study", "2024-09-23 1100").toString());
    }
}
