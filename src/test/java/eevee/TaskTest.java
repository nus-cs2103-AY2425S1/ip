package eevee;

import eevee.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represents Junit test for {@link Task}.
 */
public class TaskTest {
    private Task t;

    /**
     * Initialises a Task instance before each test.
     */
    @BeforeEach
    public void setUp() {
        t = new Task("Test");
    }

    /**
     * Tests the {@link Task#Task(String)} constructor initialises Task as intended.
     * Verifies that task is given the correct description and is marked as undone.
     */
    @Test
    public void testConstructor_initialization() {
        assertEquals("Test", t.getDescription(), "Description in initialized task should match input");
        assertFalse(t.getIsDone(), "Task should be marked as undone initially");
    }

    /**
     * Verifies that {@link Task#getStatus()} method shows that a task is initially marked as undone.
     */
    @Test
    public void testGetStatus_initialState() {
        assertEquals(0, t.getStatus(), "Task should initially be marked as undone (0)");
    }

    /**
     * Tests that the status of task is updated correctly when repeated mark and unmark commands are executed.
     */
    @Test
    public void testRepeatedMarkAndUnmark() {
        t.markAsDone();
        assertTrue(t.getIsDone(), "Task should be marked as done");

        t.unmarkAsDone();
        assertFalse(t.getIsDone(), "Task should not be marked as done");

        t.markAsDone();
        assertTrue(t.getIsDone(), "Task should again be marked as done");

        t.unmarkAsDone();
        assertFalse(t.getIsDone(), "Task should again not be marked as done");
    }
}
