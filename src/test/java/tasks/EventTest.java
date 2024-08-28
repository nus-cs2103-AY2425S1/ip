package tasks;

import exceptions.InputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new Event("", LocalDateTime.now(), LocalDateTime.now()).createTask("Meeting /from 01/01/2024 0900 /to 01/01/2024 1700");
        assertNotNull(task);
        assertEquals("[E][ ] Meeting (from: Jan 1 2024, 9:00 am to: Jan 1 2024, 5:00 pm)", task.toString());
    }

    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new Event("", LocalDateTime.now(), LocalDateTime.now()).createTask("event"));
    }

    @Test
    void testEncode() {
        LocalDateTime from = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime to = LocalDateTime.of(2024, 1, 1, 17, 0);
        Task task = new Event("Meeting", from, to);
        String encoded = task.encode();
        assertEquals("E | 0 | Meeting | 1/1/2024 0900 | 1/1/2024 1700", encoded);
    }
}
