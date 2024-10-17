package friendlybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A JUnit test for ToDo.
 */
public class ToDoTest {
    /**
     * Tests the String representation of a complete ToDo task.
     */
    @Test
    public void createToDo_markAsCompleted_correctStringOutput() {
        ToDo task = new ToDo("test task");
        task.markAsDone();
        assertEquals("[T][X] test task", task.toString());
    }

    /**
     * Tests the String representation of an incomplete ToDo task.
     */
    @Test
    public void createToDo_incomplete_correctStringOutput() {
        ToDo task = new ToDo("test task");
        assertEquals("[T][ ] test task", task.toString());
    }
}
