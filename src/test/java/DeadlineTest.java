package opus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("submit report", "2023-11-30");
        assertEquals("[D][ ] submit report (by: Nov 30 2023)", deadline.toString());
    }
}
