package lexi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void constructor_validString_initializesCorrectly() {
        Todo task = new Todo("read book"); // Create test task

        // Verify task name is set correctly
        assertEquals("read book", task.getTaskName());

        // Verify that task is initially not done
        assertFalse(task.getIsDone());
    }

    @Test
    void doTask_marksTaskAsDone() {
        Todo task = new Todo("read book"); // Create test task

        // Mark the task as done
        task.setDone(true);

        // Verify that task is marked as done
        assertTrue(task.getIsDone());
    }

    @Test
    void toString_correctStringRepresentation() {
        Todo task = new Todo("read book"); // Create test task

        // Verify the string representation of the task
        assertEquals("[T][ ] read book", task.toString(),
                "The string representation should be '[T][ ] read book'.");

        // Mark the task as done
        task.setDone(true);

        // Verify the string representation after marking the task as done
        assertEquals("[T][X] read book", task.toString(),
                "The string representation should be '[T][X] read book' after marking as done.");
    }


}
