package tick.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tick.exceptions.TickException;
import tick.tasks.ToDo;

public class TaskListTest {
    @Test
    public void markTaskAsDoneTest() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTask(0).getStatus());
    }
    @Test
    public void markTaskAsUndoneTest() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskAsDone(0);
        taskList.markTaskAsUndone(0);
        assertFalse(taskList.getTask(0).getStatus());
    }

    @Test
    public void addTaskTest() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void deleteTaskTest() throws TickException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }
}
