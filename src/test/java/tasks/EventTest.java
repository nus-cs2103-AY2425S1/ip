package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] test (from: Aug 30 2024 to: Aug 30 2024)",
                new Event("test", "2024-08-30", "2024-08-30").toString());
    }

    @Test
    public void testWriteTask() {
        Event event = new Event("test", "2024-08-30", "2024-08-30");
        assertEquals("0,3,test,2024-08-30,2024-08-30", event.writeTask());
    }
}
