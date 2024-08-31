import Mario.Tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testGetName() {
        Task task1 = new Task("Task1Name");
        assertEquals("Task1Name", task1.getName());

        Task task2 = new Task("Task2Name", true);
        assertEquals("Task2Name", task2.getName());

        Task task3 = new Task("Task3Name", false);
        assertEquals("Task3Name", task3.getName());
    }

    @Test
    public void testMarkCompleted() {
        Task task = new Task("TaskName");
        task.markCompleted();
        assertTrue(task.getIsCompleted());

    }

    @Test
    public void testMarkUncompleted() {
        Task task = new Task("TaskName");
        task.markUncompleted();
        assertFalse(task.getIsCompleted());
    }

}
