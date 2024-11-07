package chatbuddy;

import chatbuddy.task.TaskList;
import chatbuddy.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        ToDo task = new ToDo("Buy groceries");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        ToDo task = new ToDo("Buy groceries");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        ToDo task = new ToDo("Buy groceries");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }
}
