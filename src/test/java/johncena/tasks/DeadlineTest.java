package johncena.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] study (by: 2024-09-23 1100)",
                new Deadline("study", "2024-09-23 1100").toString());
    }
}
