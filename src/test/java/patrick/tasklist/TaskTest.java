package patrick.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


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
