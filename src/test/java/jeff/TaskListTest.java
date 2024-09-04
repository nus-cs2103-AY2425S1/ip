package jeff;

import jeff.exceptions.JeffException;
import jeff.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_singleTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof ToDo);
    }

    @Test
    public void deleteTask_existingTask_success() throws JeffException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void getTask_existingTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals("read book", taskList.getTask(0).getDescription());
    }
}
