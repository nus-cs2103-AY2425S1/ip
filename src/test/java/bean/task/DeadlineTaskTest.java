package bean.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeadlineTaskTest {

    private DeadlineTask deadlineTask;

    @BeforeEach
    public void setUp() {
        // Initialize a new DeadlineTask before each test
        deadlineTask = new DeadlineTask("Submit report", "2024-09-01 1800");
    }

    @Test
    public void testDeadlineTaskCreation() {
        // Test the creation of a DeadlineTask
        assertEquals("Submit report", deadlineTask.getName());
        assertFalse(deadlineTask.isDone, "Task should not be marked as done upon creation");
    }

    @Test
    public void testCompleteTask() {
        // Complete the task
        deadlineTask.completeTask();
        assertTrue(deadlineTask.isDone, "Task should be marked as done");
    }

    @Test
    public void testUndoTask() {
        // Complete the task and then undo it
        deadlineTask.completeTask();
        deadlineTask.undoTask();
        assertFalse(deadlineTask.isDone, "Task should not be marked as done");
    }

    @Test
    public void testToString() {
        // Check the string representation of a task
        assertEquals("[D][ ] Submit report (by: Sep 1 2024, 6:00 pm)", deadlineTask.toString());
        deadlineTask.completeTask();
        assertEquals("[D][X] Submit report (by: Sep 1 2024, 6:00 pm)", deadlineTask.toString());
    }

    @Test
    public void testToSaveFormat() {
        // Check the save format of the task
        assertEquals("D | 0 | Submit report | 2024-09-01 1800", deadlineTask.toSaveFormat());
        deadlineTask.completeTask();
        assertEquals("D | 1 | Submit report | 2024-09-01 1800", deadlineTask.toSaveFormat());
    }

}
