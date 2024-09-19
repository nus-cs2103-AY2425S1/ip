package espresso.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import espresso.command.InvalidCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();  // Initialize a new TaskList for each test
    }

    @Test
    void testAddTask() {
        Task task = new TodoTask("Sample Task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());  // Ensure the task list size increased
        assertEquals(task, taskList.getTask(0));  // Ensure the added task is correct
    }

    @Test
    void testRemoveTask() throws InvalidCommandException {
        Task task1 = new TodoTask("Task 1");
        Task task2 = new TodoTask("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.removeTask(0);  // Remove the first task

        assertEquals(1, taskList.size());  // Ensure the size is now 1
        assertEquals(task2, taskList.getTask(0));  // Ensure task2 is now at index 0
    }

    @Test
    void testRemoveTaskInvalidIndex() {
        Task task = new TodoTask("Task");
        taskList.addTask(task);

        // Attempt to remove a task at an invalid index
        assertThrows(InvalidCommandException.class, () -> taskList.removeTask(1));
    }

    @Test
    void testGetTaskValidIndex() throws InvalidCommandException {
        Task task = new TodoTask("Task");
        taskList.addTask(task);

        assertEquals(task, taskList.getTask(0));  // Ensure the task at index 0 is correct
    }

    @Test
    void testGetTaskInvalidIndex() {
        assertThrows(InvalidCommandException.class, () -> taskList.getTask(0));  // No tasks added yet
    }

    @Test
    void testSize() {
        assertEquals(0, taskList.size());  // Initial size should be 0

        Task task = new TodoTask("Task");
        taskList.addTask(task);

        assertEquals(1, taskList.size());  // After adding a task, size should be 1
    }

    @Test
    void testToString() {
        Task task1 = new TodoTask("Task 1");
        Task task2 = new TodoTask("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        String expected = "1.Task 1\n2.Task 2";
        assertEquals(expected, taskList.toString());  // Ensure the string representation is correct
    }
}
