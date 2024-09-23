package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testInit() {
        Task task = new Event("visit home", "2024-09-24 1700", "2024-10-01 0930");
        assertEquals("[E][ ] visit home "
                    + "(from: Sep 24 2024, 05:00 PM "
                    + "to: Oct 01 2024, 09:30 AM) tags:", task.toString());
    }
}
