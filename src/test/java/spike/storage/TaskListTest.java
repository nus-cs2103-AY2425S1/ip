package spike.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spike.exceptions.SpikeException;
import spike.tasks.ToDo;

public class TaskListTest {

    @Test
    public void addTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void deleteTask_success() throws SpikeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getTaskStatus_success() throws SpikeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        assertEquals(" ", taskList.getTaskStatus(0));
    }

    @Test
    public void markTaskDone_success() throws SpikeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskDone(0);
        assertEquals("X", taskList.getTaskStatus(0));
    }

    @Test
    public void markTaskUndone_success() throws SpikeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("homework"));
        taskList.markTaskDone(0);
        taskList.markTaskUndone(0);
        assertEquals(" ", taskList.getTaskStatus(0));
    }
}
