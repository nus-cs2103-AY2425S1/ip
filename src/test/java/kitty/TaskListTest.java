package kitty;

import kitty.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }


    @Test
    void testAddTask() {
        Task task = new Task("Test task");
        int result = taskList.addTask(task);
        assertEquals(1, result, "Task should be added successfully");
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Test task");
        taskList.addTask(task);
        String result = taskList.deleteTask(1);
        System.out.println(taskList);
        assertTrue(result.contains("Now you have 0 tasks"), "Task should be deleted successfully");
    }

    @Test
    void testMarkDone() {
        Task task = new Task("Test task");
        taskList.addTask(task);
        Task result = taskList.markDone(1);
        assertTrue(result.isDone(), "Task should be marked as done");
    }

    @Test
    void testMarkUndone() {
        Task task = new Task("Test task");
        taskList.addTask(task);
        taskList.markDone(1);
        Task result = taskList.markUndone(1);
        assertFalse(result.isDone(), "Task should be marked as undone");
    }

    @Test
    void testFindTask() {
        Task task = new Task("Test task with keyword");
        taskList.addTask(task);
        String result = taskList.findTask("keyword");
        assertTrue(result.contains("Test task with keyword"), "Task with the keyword should be found");
    }

    @Test
    void testGetData() {
        Task task1 = new Task("Test task 1");
        Task task2 = new Task("Test task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        String data = taskList.getData();
        assertTrue(data.contains("Test task 1"));
        assertTrue(data.contains("Test task 2"));
    }
}
