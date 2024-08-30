package shenhe.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task testTask;

    // Creating a concrete subclass of Task for testing purposes
    private static class TestTask extends Task {
        public TestTask(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toFileFormat() {
            return "Test | " + getStatusIcon() + " | " + description;
        }
    }

    @BeforeEach
    public void setUp() {
        testTask = new TestTask("Sample task", false);
    }

    @Test
    public void testGetStatusIcon_notDone_returnsZero() {
        assertEquals("0", testTask.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon_done_returnsOne() {
        testTask.markAsDone();
        assertEquals("1", testTask.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        testTask.markAsDone();
        assertTrue(testTask.isDone());
    }

    @Test
    public void testMarkAsUndone() {
        testTask.markAsDone();
        testTask.markAsUndone();
        assertFalse(testTask.isDone());
    }

    @Test
    public void testIsDone_initiallyFalse() {
        assertFalse(testTask.isDone());
    }

    @Test
    public void testToFileFormat() {
        assertEquals("Test | 0 | Sample task", testTask.toFileFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[0] Sample task", testTask.toString());
    }
}
