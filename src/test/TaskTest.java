package velma.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    /**
     * Test if the task is marked as done from undone and vice versa.
     */
    @Test
    public void testChangeIsDone() {
        Task task = new Task("Test task") {};
        assertFalse(task.getIsDone(), "Task should be marked as not done");

        task.changeIsDone();
        assertTrue(task.getIsDone());

        task.changeIsDone();
        assertFalse(task.getIsDone());

    }
}
