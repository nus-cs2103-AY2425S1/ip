package friday;

import friday.task.Task;
import friday.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the TaskList class.
 * Verifies that tasks can be added, deleted, and that the TaskList behaves correctly with different indices.
 */
public class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up the test environment by creating a TaskList object
     * and adding some sample Todo tasks before each test is run.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
    }

    /**
     * Tests the deletion of a task with a valid index.
     * Verifies that the task is correctly removed from the list and that the size of the list is updated.
     */
    @Test
    public void testDeleteTask_ValidIndex() {
        Task removedTask = taskList.deleteTask(0);
        assertNotNull(removedTask);
        assertEquals("Task 1", removedTask.getDescription());
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests the deletion of a task with an invalid index.
     * Verifies that no task is removed and the size of the list remains unchanged.
     */
    @Test
    public void testDeleteTask_InvalidIndex() {
        Task removedTask = taskList.deleteTask(5);
        assertNull(removedTask);
        assertEquals(2, taskList.getSize());
    }
}