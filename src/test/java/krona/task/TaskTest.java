package krona.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testIsDone() {
        Task task = new Task("Read book");
        assertFalse(task.isDone());

        task.markDone();
        assertTrue(task.isDone());
    }
}
