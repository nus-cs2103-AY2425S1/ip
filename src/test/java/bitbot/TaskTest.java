package bitbot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    Task task = new Task("Do Homework");

    @Test
    public void correctlySetsDescriptionAndStatus() {
        assertEquals("Do Homework", task.taskDescription);
        assertFalse(task.isDone, "Task should be initially marked as not done");
    }

    @Test
    public void taskIsMarkedAsDone() {
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done");
    }

    @Test
    public void taskIsMarkedAsUndone() {
        task.markAsDone();
        task.markAsUndone();
        assertFalse(task.isDone, "Task should be marked as undone");
    }

    @Test
    public void finalString_unmarkedTask_returnsCorrectString() {
        assertEquals("[ ] Do Homework", task.finalString());
    }

    @Test
    public void finalString_markedTask_returnsCorrectString() {
        task.markAsDone();
        assertEquals("[X] Do Homework", task.finalString());
    }

    @Test
    public void toFileFormat_defaultImplementation_returnsEmptyString() {
        assertEquals(" ", task.toFileFormat());
    }
}
