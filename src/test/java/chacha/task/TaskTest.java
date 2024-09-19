package chacha.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Task class.
 */
public class TaskTest {
    @Test
    public void testMarkDone_expectedOutcome() {
        Task task = new Task("Test task", false);
        task.markDone();
        assertTrue(task.isDone, "Task should be marked as done");
    }

    @Test
    public void testPrintTask_expectedOutcome() {
        Task doneTask = new Task("Test task marked done", true);
        assertEquals("[X] Test task marked done", doneTask.printTask(),
                "PrintTask should show task as done with 'X'");

        Task undoneTask = new Task("Test task marked undone", false);
        assertEquals("[ ] Test task undone", undoneTask.printTask(),
                "PrintTask should show task as not done with ' '");
    }

    @Test
    public void testCompareText_expectedOutcome() {
        Task task = new Task("Sample description", false);
        assertTrue(task.compareText("Sample"),
                "CompareText should return true for substring 'Sample'");
        assertFalse(task.compareText("Apple"),
                "CompareText should return false for non-matching substring");
    }
}
