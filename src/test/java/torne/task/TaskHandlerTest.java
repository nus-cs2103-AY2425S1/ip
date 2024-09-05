package torne.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import torne.exception.TorneInvalidCommandException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskHandlerTest {

    private TaskHandler taskHandler;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() throws TorneInvalidCommandException {
        taskHandler = new TaskHandler();
        task1 = new Task("Task 1");
        task2 = new Task("Task 2");
    }

    @Test
    void addTask_addTwoTasks_twoTasks() {
        taskHandler.addTask(task1);
        taskHandler.addTask(task2);
        assertEquals(2, taskHandler.getTaskCount(), "Task count should be 2 after adding two tasks.");
    }

    @Test
    void addAndGetTask_twoTasksAdded_sameOrder() {
        taskHandler.addTask(task1);
        taskHandler.addTask(task2);
        assertEquals(task1, taskHandler.getTask(0), "The first task should be 'Task 1'.");
        assertEquals(task2, taskHandler.getTask(1), "The second task should be 'Task 2'.");
    }

    @Test
    void removeTask_removeOneByOne_sameOrder() {
        taskHandler.addTask(task1);
        taskHandler.addTask(task2);

        Task removedTask = taskHandler.removeTask(0);
        assertEquals(task1, removedTask, "The removed task should be 'Task 1'.");
        assertEquals(1, taskHandler.getTaskCount(), "Task count should be 1 after removing one task.");
        assertEquals(task2, taskHandler.getTask(0), "The remaining task should be 'Task 2'.");
    }

    @Test
    void getTaskCount_upToTwoTasks_correctCount() {
        assertEquals(0, taskHandler.getTaskCount(), "Task count should be 0 initially.");
        taskHandler.addTask(task1);
        assertEquals(1, taskHandler.getTaskCount(), "Task count should be 1 after adding one task.");
        taskHandler.addTask(task2);
        assertEquals(2, taskHandler.getTaskCount(), "Task count should be 2 after adding two tasks.");
    }

    @Test
    void getTaskListString_twoTasks_correctString() {
        taskHandler.addTask(task1);
        taskHandler.addTask(task2);
        String expected = "1. [ ] Task 1\n2. [ ] Task 2";
        assertEquals(expected, taskHandler.getTaskListString(), "Task list string should match the expected format.");
    }

    @Test
    void getTaskList_twoTasks_correctList() {
        taskHandler.addTask(task1);
        taskHandler.addTask(task2);
        ArrayList<Task> taskList = taskHandler.getTaskList();
        assertEquals(2, taskList.size(), "Task list size should be 2 after adding two tasks.");
        assertEquals(task1, taskList.get(0), "The first task in the list should be 'Task 1'.");
        assertEquals(task2, taskList.get(1), "The second task in the list should be 'Task 2'.");
    }
}
