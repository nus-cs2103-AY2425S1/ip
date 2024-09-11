package monobot.util;

import monobot.exception.MonoBotException;
import monobot.task.Task;
import monobot.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    void testDeleteTask() throws MonoBotException {
        List<Integer> integerList = new LinkedList<>();
        integerList.add(0);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTasks(integerList);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    void testGetTask() {
        taskList.addTask(task1);
        assertEquals(task1, taskList.getTask(0));
    }

    @Test
    void testMarkTasks() throws MonoBotException {
        List<Integer> integerList = new LinkedList<>();
        integerList.add(0);
        taskList.addTask(task1);
        taskList.markTasks(integerList);
        assertTrue(taskList.getTask(0).getIsDone());
    }

    @Test
    void testUnmarkTasks() throws MonoBotException {
        List<Integer> integerList = new LinkedList<>();
        integerList.add(0);
        taskList.addTask(task1);
        taskList.markTasks(integerList);
        taskList.unmarkTasks(integerList);
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
