package taskType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskInitialization() {
        Task task = new Task("Test task");
        assertEquals("Test task", task.getDescription());
        assertFalse(task.getStatusIcon().equals("[X]"));
    }

    @Test
    public void testGetDescription() {
        Task task = new Task("test task");
        assertEquals("test task", task.getDescription());
    }

    @Test
    public void testGetStatusIconWhenNotDone() {
        Task task = new Task("Another task");
        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIconWhenDone() {
        Task task = new Task("Fire off!");
        task.setDone(true);
        assertEquals("[X]", task.getStatusIcon());
    }

}
