package krona.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testIsDone() {
        Task task = new Task("Read book");
        assertFalse(task.isDone());

        task.markDone();
        assertTrue(task.isDone());
    }
}
