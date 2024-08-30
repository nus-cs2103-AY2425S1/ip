package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void addTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
    }

    @Test
    void getTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        List<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void getTasksCount() {
        assertEquals(0, taskList.getTasksCount());

        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(2, taskList.getTasksCount());
    }

    @Test
    void deleteTask_valid() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(1);
        assertEquals(1, taskList.getTasksCount());
        assertEquals(task2, taskList.getTasks().get(0));
    }

    @Test
    void deleteTask_outOfBounds() {
        taskList.addTask(task1);
        taskList.addTask(task2);

        // No tasks should be deleted if command invalid
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(3));
        assertEquals(2, taskList.getTasksCount());
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task2, taskList.getTasks().get(1));
    }

    @Test
    void markTask_valid() {
        taskList.addTask(task1);
        taskList.updateTaskStatus(1, true);
        assertTrue(task1.isDone);
    }

    @Test
    void markTask_outOfBounds() {
        taskList.addTask(task1);

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.updateTaskStatus(3, true));
        assertFalse(task1.isDone);
    }
}
