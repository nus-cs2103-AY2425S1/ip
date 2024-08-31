package eevee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskTest {
    private Task t;

    @BeforeEach
    public void setUp() {
        t = new Task("Test");
    }

    @Test
    public void testConstructor_initialization() {
        assertEquals("Test", t.description, "Description in initialized task should match input");
        assertFalse(t.isDone, "Task should be marked as undone initially");
    }

    @Test
    public void testGetStatus_initialState() {
        assertEquals(0, t.getStatus(), "Task should initially be marked as undone (0)");
    }

    @Test
    public void testRepeatedMarkAndUnmark() {
        t.markAsDone();
        assertTrue(t.isDone, "Task should be marked as done");

        t.unmarkAsDone();
        assertFalse(t.isDone, "Task should not be marked as done");

        t.markAsDone();
        assertTrue(t.isDone, "Task should again be marked as done");

        t.unmarkAsDone();
        assertFalse(t.isDone, "Task should again not be marked as done");
    }
}
