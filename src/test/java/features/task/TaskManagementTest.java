package features.task;

import data.TaskDAOStub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TaskManagementTest {

    private TaskManagement taskManagement;
    private TaskDAOStub taskDAO;

    @BeforeEach
    public void setUp() {
        taskDAO = new TaskDAOStub();
        taskManagement = new TaskManagement(taskDAO);
    }

    @Test
    public void testAddTask() {
        Task newTask = new TestTask("Read a book");
        taskManagement.add(newTask);
        assertEquals(1, taskManagement.length);
        List<Task> tasks = taskDAO.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals(newTask, tasks.get(0));
    }

    @Test
    public void testHandleItemMark() {
        Task task = new TestTask("Complete homework");
        taskDAO.addTask(task);
        int taskId = task.getId();
        taskManagement.handleItem("mark", taskId);
        assertTrue(task.getIsMarked());
    }

    @Test
    public void testHandleItemUnmark() {
        Task task = new TestTask("Attend meeting");
        taskDAO.addTask(task);
        int taskId = task.getId();
        task.mark();
        taskManagement.handleItem("unmark", taskId);
        assertFalse(task.getIsMarked());
    }
}
