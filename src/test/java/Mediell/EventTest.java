package Mediell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void init_success(){
        Event event = new Event("task/from 2023-01-01 /to 2024-01-02");
        assertEquals("[E][ ] task(from: 2023-01-01 to: 2024-01-02)", event.toString());
    }

    @Test
    public void init_exceptionThrown() {
        try {
            Event event = new Event("task");
        } catch (Exception e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    public void taskToStorageFormat_success() {
        // base case
        Event event = new Event("task/from 2023-01-01 /to 2024-01-02");
        assertEquals("E|2023-01-01|2024-01-02|0|task", event.taskToStorageFormat());

        // a marked task
        event.markAsCompleted();
        assertEquals("E|2023-01-01|2024-01-02|1|task", event.taskToStorageFormat());
    }
}
