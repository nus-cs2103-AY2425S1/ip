package stan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stan.tasks.Task;


/**
 * Unit tests for the TaskList class.
 * This class tests various operations on the TaskList, such as adding, retrieving tasks, and verifying list size.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a new TaskList before each test.
     * This method is executed before every individual test case.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests the behavior of adding a task to the TaskList.
     * Ensures that the task is correctly added and the size of the list increases.
     */
    @Test
    void testAddTask() {
        Task task = new MockTask("Test Task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    /**
     * Tests the behavior of removing a task from the TaskList.
     * Ensures that the correct task is removed and the size of the list decreases.
     */
    @Test
    void testRemoveTask() {
        Task task1 = new MockTask("Task 1");
        Task task2 = new MockTask("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        Task removedTask = taskList.remove(0);
        assertEquals(1, taskList.size());
        assertEquals(task1, removedTask);
        assertEquals(task2, taskList.get(0));
    }

    /**
     * Tests the behavior of retrieving a task from the TaskList.
     * Ensures that the correct task is retrieved by its index.
     */
    @Test
    void testGetTask() {
        Task task = new MockTask("Test Task");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    /**
     * Tests the behavior of checking the size of the TaskList.
     * Ensures that the size reflects the number of tasks correctly.
     */
    @Test
    void testSize() {
        assertEquals(0, taskList.size());
        taskList.add(new MockTask("Task 1"));
        assertEquals(1, taskList.size());
    }

    /**
     * Tests the behavior of removing a task with an invalid index from the TaskList.
     * Ensures that an IndexOutOfBoundsException is thrown and the size of the list remains unchanged.
     */
    @Test
    void testRemoveTaskInvalidIndex() {
        taskList.add(new MockTask("Task 1"));
        taskList.add(new MockTask("Task 2"));
        assertEquals(2, taskList.size());

        try {
            taskList.remove(2); // This should throw an IndexOutOfBoundsException
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(2, taskList.size()); // Ensure size has not changed
        }
    }
}
