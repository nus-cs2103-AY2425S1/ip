package bopes.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void testConstructorAndGetters() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("Test task", todo.description);
        assertFalse(todo.isDone);

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("Completed task", todoDone.description);
        assertTrue(todoDone.isDone);
    }

    @Test
    public void testToString() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("[T][ ] Test task", todo.toString());

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("[T][X] Completed task", todoDone.toString());
    }

    @Test
    public void testToFileFormat() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("T | 0 | Test task", todo.toFileFormat());

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("T | 1 | Completed task", todoDone.toFileFormat());
    }
}
