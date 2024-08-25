package calebyyy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import calebyyy.Tasks.Task;


public class TaskListTest {

    @Test
    public void testAddTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);
        Task task = new Task("Test Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.getTasks().get(0));
    }

    @Test
    public void testDeleteTask() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui);
        Task task = new Task("Test Task");
        taskList.addTask(task);
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTaskCount());
    }
}