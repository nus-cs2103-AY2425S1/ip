package fridayproject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskListTest {
    private TaskList taskList;

    @Before
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Tasks todo = new Todo("Read book");
        taskList.addTask(todo);
        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }

    @Test
    public void testDeleteTask() {
        Tasks todo = new Todo("Read book");
        taskList.addTask(todo);
        Tasks deletedTask = taskList.deleteTask(0);
        assertEquals("Read book", deletedTask.getDescription());
        assertEquals(0, taskList.size());
    }
}