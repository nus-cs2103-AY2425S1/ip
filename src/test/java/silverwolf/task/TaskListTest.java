package silverwolf.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TaskList class to verify the correct functionality of the TaskList in managing tasks.
 */
public class TaskListTest {
    private TaskList taskList;

    /**
     * Initializes an empty TaskList before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList(); // Initialize an empty TaskList
    }

    /**
     * Tests that a new TaskList is correctly initialized as empty.
     * Verifies that no tasks are present when the TaskList is first created.
     */
    @Test
    void testInitialization() {
        // Test that a new TaskList is initialized as empty
        assertTrue(taskList.getTasks().isEmpty(), "TaskList should be empty upon initialization");
    }

    /**
     * Tests adding a single task to the TaskList.
     * Verifies that the task is added correctly and is retrievable.
     */
    @Test
    void testAddTask() {
        // Test adding a task to the TaskList
        Task task = new Todo("Do CS2106 Tutorial 3"); // Assume Todo is a subclass of Task
        taskList.addTask(task);
        assertEquals(1, taskList.getSize(), "TaskList size should be 1 after adding a task");
        assertEquals(task, taskList.getTask(0), "The task added should be retrievable");
    }

    /**
     * Tests the size of the TaskList after adding multiple tasks.
     * Verifies that the size matches the number of tasks added.
     */
    @Test
    void testGetSize() {
        // Test the size of the TaskList
        Task task1 = new Todo("Complete homework");
        Task task2 = new Todo("Read book");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize(), "TaskList size should match the number of tasks added");
    }

    /**
     * Tests deleting a task from the TaskList.
     * Verifies that tasks can be added and checked for existence.
     */
    @Test
    void testDeleteTask() {
        // Test retrieval of all tasks
        Task task1 = new Todo("Complete CS2103T IP A-OOP");
        Task task2 = new Todo("Read ES2660 readings");
        taskList.addTask(task1);
        taskList.addTask(task2);
        List<Task> tasks = taskList.getTasks();
        assertTrue(tasks.contains(task1), "TaskList should contain the added tasks");
        assertTrue(tasks.contains(task2), "TaskList should contain the added tasks");
    }

    /**
     * Tests retrieving a task by its index.
     * Verifies that the task retrieved matches the task that was added.
     */
    @Test
    void testGetTaskByIndex() {
        // Test retrieving a task by index
        Task task = new Todo("Complete homework");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0), "The task retrieved should match the task added");
    }

    /**
     * Tests retrieving a task by an invalid index.
     * Verifies that an IndexOutOfBoundsException is thrown for invalid indices.
     */
    @Test
    void testGetTaskByInvalidIndex() {
        // Test retrieving a task by invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0),
                "Should throw IndexOutOfBoundsException for invalid index");
    }
}
