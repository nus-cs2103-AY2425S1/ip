package alfred.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new TaskStub("Read book", false);
        task2 = new TaskStub("Write code", false);
    }

    @Test
    void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
    }

    @Test
    void testGetTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        List<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void testGetTasksCount() {
        assertEquals(0, taskList.getTasksCount());

        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(2, taskList.getTasksCount());
    }

    @Test
    void testDeleteTask_Valid() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task2, taskList.getTasks().get(0));
    }

    @Test
    void testDeleteTask_OutOfBounds() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        // No tasks should be deleted if command invalid
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.deleteTask(3));
        assertEquals(2, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task2, taskList.getTasks().get(1));
    }

    @Test
    void testMarkTask_Valid() {
        taskList.addTask(task1);
        taskList.updateTaskStatus(1, true);
        assertTrue(task1.isDone);
    }

    @Test
    void testMarkTask_OutOfBounds() {
        taskList.addTask(task1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.updateTaskStatus(3, true));
        assertFalse(task1.isDone);
    }
}
