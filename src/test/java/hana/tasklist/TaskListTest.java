package hana.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hana.HanaException;
import hana.task.Task;
import hana.task.ToDo;

public class TaskListTest {
    private TaskList taskList = new TaskList();


    @Test
    public void addTask_validTask_taskAdded() throws HanaException {
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(task, taskList.getTasks().get(0));
    }

    @Test
    public void deleteTask_validTask_taskDeleted() throws HanaException {
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        taskList.deleteTask(1);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void markTask_validTask_taskMarked() throws HanaException {
        Task task = new ToDo("Test task");
        taskList.addTask(task);
        taskList.markTask(1, true);
        assertEquals(true, taskList.getTasks().get(0).getIsDone());
        taskList.markTask(1, false);
        assertEquals(false, taskList.getTasks().get(0).getIsDone());
    }
}
