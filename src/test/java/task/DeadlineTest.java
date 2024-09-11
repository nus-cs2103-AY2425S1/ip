import task.Deadline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    
    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("complete assignment", "2024-09-14 2300");
        assertEquals("[D][ ] complete assignment (by: Sep 14 2024, 11:00 PM)");
    }
}
