package shenhe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shenhe.task.Task;
import shenhe.task.Todo;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Initialize an empty TaskList before each test
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new Todo("Test Task", true);
        taskList.addTask(task);
        assertEquals(1, taskList.getSize(), "Task list size should be 1 after adding a task.");
    }

    @Test
    public void testAddTask2() {
        Task task = new Todo("Test Task", true);
        taskList.addTask(task);
        Task retrievedTask = taskList.getTask(0);
        assertEquals(task, retrievedTask, "Retrieved task should match the added task.");
    }

    @Test
    public void testDeleteTask() {
        Task task1 = new Todo("Test Task 1", true);
        Task task2 = new Todo("Test Task 2", true);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTask(0);
        assertEquals(1, taskList.getSize(), "Task list size should be 1 after deleting a task.");
        assertEquals(task2, taskList.getTask(0), "Remaining task should be the second added task.");
    }

    @Test
    public void testFindTask() {
        Task task1 = new Todo("Buy groceries", true);
        Task task2 = new Todo("Read a book", false);
        Task task3 = new Todo("Buy new shoes", false);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        List<Task> matchingTasks = taskList.findTasks("buy");

        assertEquals(2, matchingTasks.size(), "Should find two tasks with the keyword 'buy'.");
        assertTrue(matchingTasks.contains(task1), "Task 1 should be in the matching tasks.");
        assertTrue(matchingTasks.contains(task3), "Task 3 should be in the matching tasks.");
    }

    @Test
    public void testFindTask2() {
        Task task1 = new Todo("Buy groceries", true);
        Task task2 = new Todo("Read a book", false);
        taskList.addTask(task1);
        taskList.addTask(task2);

        List<Task> matchingTasks = taskList.findTasks("exercise");

        assertEquals(0, matchingTasks.size(), "Should return an empty list if no task matches the keyword.");
    }

    @Test
    public void testInitialiseWithExistingList() {
        List<Task> initialTasks = new ArrayList<>();
        Task task1 = new Todo("Task 1", true);
        Task task2 = new Todo("Task 2", false);
        initialTasks.add(task1);
        initialTasks.add(task2);

        TaskList taskList = new TaskList(initialTasks);

        assertEquals(2, taskList.getSize(), "Task list size should be 2 when initialized with two tasks.");
        assertEquals(task1, taskList.getTask(0), "First task should be Task 1.");
        assertEquals(task2, taskList.getTask(1), "Second task should be Task 2.");
    }

    @Test
    public void testEmptyTaskList() {
        assertEquals(0, taskList.getSize(), "Task list size should be 0 for an empty list.");
    }
}
