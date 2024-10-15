import task.Task;
import task.TaskList;
import task.ToDo;
import utility.Parser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskListTest {
    @Test
    public void testAdd(){
        Task task = new ToDo("test1");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    public void taskDelete(){
        Task task = new ToDo("test2");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTaskCount());
    }
}