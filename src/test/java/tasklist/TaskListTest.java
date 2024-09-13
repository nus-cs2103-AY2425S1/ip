package tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddTask() {
        Task task = new Task("sample task");
        taskList.addTask(task);

        assertEquals(1, taskList.getBotMemory().size());
        assertEquals("sample task", taskList.getBotMemory().get(0).description);
    }

    @Test
    public void testRemoveTask() {
        // Add a sample task and remove it
        Task task = new Task("sample task");
        taskList.addTask(task);
        taskList.removeTask(0);

        // Check if the task was removed
        assertEquals(0, taskList.getBotMemory().size());
    }

    @Test
    public void testListToString_noTasks() {
        // Check output when there are no tasks
        String expectedOutput = "No items in your list";
        String actualOutput = taskList.listToString();

        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testListToString_multipleTasks() {
        // Add multiple tasks
        Task task1 = new Task("task 1");
        Task task2 = new Task("task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        // Check output when there are multiple tasks
        String expectedOutput = "Here are the tasks in your list: \n1. [T][ ] task 1\n2. [T][ ] task 2";
        String actualOutput = taskList.listToString();

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
