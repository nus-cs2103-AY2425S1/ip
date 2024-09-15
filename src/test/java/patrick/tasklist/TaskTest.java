package patrick.tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMarkTaskAsDone() {
        Task task = new Task("Eat Dinner");
        task.markAsDone();
        assertTrue(task.isDone);
        assertEquals("X | Eat Dinner", task.toString());
    }

    @Test
    public void testMarkTaskAsNotDone() {
        Task task = new Task("Do work");
        task.markAsUndone();
        assertFalse(task.isDone);
        assertEquals("O | Do work", task.toString());
    }
}