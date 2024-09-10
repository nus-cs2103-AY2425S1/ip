package bean.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    private TodoTask todoTask;

    @BeforeEach
    public void setUp() {
        // Initialize a new TodoTask before each test
        todoTask = new TodoTask("Read a book");
    }

    @Test
    public void testTodoTaskCreation() {
        // Test the creation of a TodoTask
        assertEquals("Read a book", todoTask.getName());
        assertFalse(todoTask.isDone, "Task should not be marked as done upon creation");
    }

    @Test
    public void testCompleteTask() {
        // Mark the task as done
        todoTask.completeTask();
        assertTrue(todoTask.isDone, "Task should be marked as done");
    }

    @Test
    public void testUndoTask() {
        // Mark the task as done and then as not done
        todoTask.completeTask();
        todoTask.undoTask();
        assertFalse(todoTask.isDone, "Task should not be marked as done");
    }

    @Test
    public void testToString() {
        // Check the string representation of a task
        assertEquals("[T][ ] Read a book", todoTask.toString());
        todoTask.completeTask();
        assertEquals("[T][X] Read a book", todoTask.toString());
    }

    @Test
    public void testToSaveFormat() {
        // Check the save format of the task
        assertEquals("T | 0 | Read a book", todoTask.toSaveFormat());
        todoTask.completeTask();
        assertEquals("T | 1 | Read a book", todoTask.toSaveFormat());
    }
}
