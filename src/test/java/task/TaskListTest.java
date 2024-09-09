package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the TaskList class.
 */
public class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a new TaskList instance before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests that a task can be added to the TaskList.
     */
    @Test
    public void testAddTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Test task", taskList.getTasks().get(0).getName());
    }

    /**
     * Tests that a task can be deleted from the TaskList.
     */
    @Test
    public void testDeleteTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.delete(0);
        assertEquals(0, taskList.getTasks().size());
    }

    /**
     * Tests that a task can be marked as done in the TaskList.
     */
    @Test
    public void testMarkTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.mark(0);
        assertTrue(taskList.getTasks().get(0).isDone());
    }

    /**
     * Tests that a task can be unmarked as not done in the TaskList.
     */
    @Test
    public void testUnmarkTask() {
        Todo todo = new Todo("Test task");
        taskList.add(todo);
        taskList.mark(0);
        taskList.unmark(0);
        assertFalse(taskList.getTasks().get(0).isDone());
    }
}
