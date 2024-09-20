package totoro.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTask extends Task {
    public TestTask(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return description;
    }
}

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new TestTask("Test Task");
    }

    @Test
    public void testGetDescription() {
        assertEquals("Test Task", task.getDescription());
    }

    @Test
    public void testInitialStatusIsNotDone() {
        assertFalse(task.isDone);
    }

    @Test
    public void testMarkAsDone() {
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testMarkAsNotDone() {
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("[ ]", task.getStatusIcon());
        task.markAsDone();
        assertEquals("[X]", task.getStatusIcon());
    }

    @Test
    public void testContainsKeyword() {
        assertTrue(task.containsKeyword("Test"));
        assertFalse(task.containsKeyword("Not in Description"));
    }
}
