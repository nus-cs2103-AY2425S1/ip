package tasks;

import exceptions.InputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new Deadline("", LocalDateTime.now()).createTask("Submit assignment /by 15/10/2024 2359");
        assertNotNull(task);
        assertEquals("[D][ ] Submit assignment (by: Oct 15 2024, 11:59 pm)", task.toString());
    }

    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new Deadline("", LocalDateTime.now()).createTask("deadline"));
    }

    @Test
    void testEncode() {
        LocalDateTime date = LocalDateTime.of(2024, 10, 15, 23, 59);
        Task task = new Deadline("Submit assignment", date);
        String encoded = task.encode();
        assertEquals("D | 0 | Submit assignment | 15/10/2024 2359", encoded);
    }
}
