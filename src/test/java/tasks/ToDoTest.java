package tasks;

import exceptions.InputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new ToDo("Test task").createTask("Test task");
        assertNotNull(task);
        assertEquals("[T][ ] Test task", task.toString());
    }

    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new ToDo("").createTask("todo"));
    }

    @Test
    void testEncode() {
        Task task = new ToDo("Test task");
        String encoded = task.encode();
        assertEquals("T | 0 | Test task", encoded);
    }
}
