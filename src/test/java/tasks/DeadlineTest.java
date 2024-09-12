package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] test (by: Aug 30 2024)",
                new Deadline("test", "2024-08-30").toString());
    }

    @Test
    public void testWriteTask() {
        Deadline deadline = new Deadline("test", "2024-08-30");
        assertEquals("0,3,test,2024-08-30", deadline.writeTask());
    }
}
