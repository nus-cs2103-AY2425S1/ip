package vinegar.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the TaskList class functionality.
 * <p>
 * This class covers tests for adding tasks, marking tasks as done or not done, and handling invalid tasks.
 */
public class TaskListTest {

    private TaskList taskList;

    /**
     * Initializes the TaskList before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests adding a valid task to the TaskList.
     */
    @Test
    public void testAddTask_validTask_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().contains(task));
    }

    /**
     * Tests marking a task as done in the TaskList.
     */
    @Test
    public void testMarkTaskAsDone_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        task.markAsDone();

        // Test if the task is marked as done
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Tests marking a task as not done in the TaskList.
     */
    @Test
    public void testMarkTaskAsNotDone_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        task.markAsDone();  // Mark as done first
        task.markAsNotDone();  // Then mark as not done

        // Test if the task is marked as not done
        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Tests adding a null task to the TaskList.
     * <p>
     * An exception is expected when trying to add a null task.
     */
    @Test
    public void testAddNullTask_failure() {
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }
}
