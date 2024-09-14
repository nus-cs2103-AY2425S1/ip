package bangmang;

import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;
import bangmang.tasks.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() throws InvalidTaskFormatException {
        Task task = new Task("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testDeleteInvalidTask() {
        Exception exception = assertThrows(InvalidTaskFormatException.class, () -> {
            taskList.delete(0);
        });
        assertEquals("Task number out of range.", exception.getMessage());
    }

    @Test
    public void testDeleteValidTask() throws InvalidTaskFormatException {
        Task task = new Task("Test task");
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testMarkTask() throws InvalidTaskFormatException {
        Task task = new Task("Test task");
        taskList.add(task);
        taskList.markTask(0);
        assertTrue(taskList.getTasks().get(0).getIsDone());
    }

    @Test
    public void testUnmarkTask() throws InvalidTaskFormatException {
        Task task = new Task("Test task");
        taskList.add(task);
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.getTasks().get(0).getIsDone());
    }
}
