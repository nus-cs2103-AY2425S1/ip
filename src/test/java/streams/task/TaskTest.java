package streams.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class TaskTest {

    @Test
    public void testToDoTask() {
        ToDoTask task = new ToDoTask("Read book");
        assertFalse(task.isCompleted());
        assertEquals("[T][ ] Read book", task.toString());
        task.markAsDone();
        assertTrue(task.isCompleted());
        assertEquals("[T][X] Read book", task.toString());
    }

    @Test
    public void testDeadlineTask() {
        LocalDateTime deadline = LocalDateTime.of(2023, 12, 31, 23, 59);
        DeadlineTask task = new DeadlineTask("Submit report", deadline);
        assertFalse(task.isCompleted());
        assertEquals("[D][ ] Submit report (by: Dec 31 2023, 23:59)", task.toString());
        task.markAsDone();
        assertTrue(task.isCompleted());
        assertEquals("[D][X] Submit report (by: Dec 31 2023, 23:59)", task.toString());
    }

    @Test
    public void testEventTask() {
        LocalDateTime from = LocalDateTime.of(2023, 12, 25, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 12, 25, 12, 0);
        EventTask task = new EventTask("Team meeting", from, to);
        assertFalse(task.isCompleted());
        assertEquals("[E][ ] Team meeting (from: Dec 25 2023, 10:00 to: Dec 25 2023, 12:00)", task.toString());
        task.markAsDone();
        assertTrue(task.isCompleted());
        assertEquals("[E][X] Team meeting (from: Dec 25 2023, 10:00 to: Dec 25 2023, 12:00)", task.toString());
    }
}