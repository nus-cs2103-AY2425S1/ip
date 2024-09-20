package Johnson.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new ToDo("Test Task");
        assertTrue(taskList.addTask(task));
        assertEquals(1, taskList.taskCount());
    }

    @Test
    void testGetTask() {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void testMarkTask() {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);
        assertTrue(taskList.markTask(0));
        assertTrue(taskList.getTask(0).getTaskStatus());
    }

    @Test
    void testUnmarkTask() {
        Task task = new ToDo("Test Task");
        task.completeTask();
        taskList.addTask(task);
        assertFalse(taskList.unmarkTask(0));
        assertFalse(taskList.getTask(0).getTaskStatus());
    }

    @Test
    void testDeleteTask() {
        Task task = new ToDo("Test Task");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.taskCount());
    }

    @Test
    void testTaskCount() {
        assertEquals(0, taskList.taskCount());
        taskList.addTask(new ToDo("Test Task 1"));
        taskList.addTask(new ToDo("Test Task 2"));
        assertEquals(2, taskList.taskCount());
    }

    @Test
    void testToString() {
        Task task1 = new ToDo("Test Task 1");
        Task task2 = new ToDo("Test Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        String expected = "1. [T][ ] Test Task 1 []\n2. [T][ ] Test Task 2 []\n";
        assertEquals(expected, taskList.toString());
    }

    @Test
    void testFindTasks() {
        Task task1 = new ToDo("Test Task 1");
        Task task2 = new ToDo("Another Task");
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> foundTasks = taskList.findTasks("Test");
        assertEquals(1, foundTasks.size());
        assertEquals(task1, foundTasks.get(0));
    }

    @Test
    void testGetTasksWithTag() {
        Task task1 = new ToDo("Test Task 1", "tag1");
        Task task2 = new ToDo("Another Task", "tag2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> foundTasks = taskList.getTasksWithTag("tag1");
        assertEquals(1, foundTasks.size());
        assertEquals(task1, foundTasks.get(0));
    }
}