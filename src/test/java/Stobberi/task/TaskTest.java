package stobberi.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Todo("Read book", "0");
    }

    @Test
    public void testInitialStatus() {
        // By default, the task should not be marked as done
        assertFalse(task.isDone(), "Task should not be done initially.");
        assertEquals(" ", task.getStatusIcon(), "Status icon should be an empty space initially.");
    }

    @Test
    public void testSetDone() {
        // Mark the task as done
        task.setDone();
        assertTrue(task.isDone(), "Task should be marked as done.");
        assertEquals("X", task.getStatusIcon(), "Status icon should be 'X' when task is done.");
    }

    @Test
    public void testSetNotDone() {
        // Mark the task as done and then as not done
        task.setDone();
        task.setNotDone();
        assertFalse(task.isDone(), "Task should be marked as not done.");
        assertEquals(" ", task.getStatusIcon(), "Status icon should be an empty space when task is not done.");
    }

    @Test
    public void testHasWord() {
        // Test if the task description contains a specific word
        assertTrue(task.hasWord("Read"), "Task description should contain the word 'Read'.");
        assertFalse(task.hasWord("Write"), "Task description should not contain the word 'Write'.");
    }

    @Test
    public void testIsSameDescription() {
        // Test the description similarity method
        assertTrue(task.isSame("Read book"), "Task description should match 'Read book'.");
        assertFalse(task.isSame("Write essay"), "Task description should not match 'Write essay'.");
    }

    @Test
    public void testGetDescription() {
        // Test if the description is returned correctly
        assertEquals("Read book", task.getDescription(), "Description should be 'Read book'.");
    }

    @Test
    public void testToString() {
        // Test the string representation of the task
        assertEquals("[T] [ ] Read book", task.toString(), "toString should return '[ ] Read book' when task is not done.");

        // After marking the task as done
        task.setDone();
        assertEquals("[T] [X] Read book", task.toString(), "toString should return '[X] Read book' when task is done.");
    }
}
