package bangmang.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bangmang.exception.InvalidTaskFormatException;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testDeleteTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testGetTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testMarkTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        taskList.add(task);
        taskList.markTask(0);
        assertTrue(taskList.get(0).getIsDone());
    }

    @Test
    public void testUnmarkTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        taskList.add(task);
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.get(0).getIsDone());
    }
}
