package monobot.util;

import monobot.task.Task;
import monobot.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new Todo("Task 1");
        task2 = new Todo("Task 2");
    }

    @Test
    void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.getTask(0));
    }

    @Test
    void testDeleteTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    void testGetTask() {
        taskList.addTask(task1);
        assertEquals(task1, taskList.getTask(0));
    }

    @Test
    void testMarkTask() {
        taskList.addTask(task1);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).getIsDone());
    }

    @Test
    void testUnmarkTask() {
        taskList.addTask(task1);
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).getIsDone());
    }

    @Test
    void testSize() {
        assertEquals(0, taskList.size());
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(taskList.isEmpty());
        taskList.addTask(task1);
        assertFalse(taskList.isEmpty());
    }

    @Test
    void testGetTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

    @Test
    void testConstructorWithTasks() {
        ArrayList<Task> initialTasks = new ArrayList<>();
        initialTasks.add(task1);
        initialTasks.add(task2);
        TaskList newTaskList = new TaskList(initialTasks);
        assertEquals(2, newTaskList.size());
        assertEquals(task1, newTaskList.getTask(0));
        assertEquals(task2, newTaskList.getTask(1));
    }

    @Test
    void testGetTasksReturnsNewList() {
        taskList.addTask(task1);
        ArrayList<Task> tasks = taskList.getTasks();
        tasks.add(task2);
        assertEquals(1, taskList.size());
        assertEquals(2, tasks.size());
    }
}
