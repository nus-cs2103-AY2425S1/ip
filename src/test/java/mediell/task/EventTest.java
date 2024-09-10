package mediell.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void init_success(){
        Event event = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        assertEquals("[E][ ] task(from: 2023-01-01 to: 2024-01-02)", event.toString());
    }

    @Test
    public void taskToStorageFormat_success() {
        // base case
        Event event = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        assertEquals("E|2023-01-01|2024-01-02|0|task", event.taskToStorageFormat());

        // a marked task
        event.markCompleted();
        assertEquals("E|2023-01-01|2024-01-02|1|task", event.taskToStorageFormat());
    }
}
