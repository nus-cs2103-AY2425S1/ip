package hien.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hien.exception.HienException;
import hien.task.Event;
import hien.task.Task;
import hien.task.Todo;
import hien.task.Deadline;


/**
 * Test class for the TaskList class.
 * This class contains unit tests for the methods in the TaskList class.
 */
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
    }

    /**
     * Tests that a task can be added correctly to the task list.
     * Verifies that the task list size increases and the task description is correctly stored.
     *
     * @throws HienException if there is an issue with adding the task
     */
    @Test
    public void testAddTask() throws HienException {
        Task task = new Todo("Test");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals("Test", taskList.getTask(0).getDescription());
    }

    /**
     * Tests that a task can be successfully removed from the task list.
     * Verifies that the task list becomes empty after removing the task.
     *
     * @throws HienException if there is an issue with adding the task
     */
    @Test
    public void testDeleteTask() throws HienException {
        Task task = new Todo("test");
        taskList.addTask(task);
        Task removedTask = taskList.deleteTask(0);
        assertEquals(task, removedTask);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testAnomalyDeleteTask() throws HienException {
        try {
            taskList.deleteTask(0);
            fail("Expected HienException");
        } catch (HienException e) {
        }
    }

}

