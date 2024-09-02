package kai;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import kai.tasks.Task;
import kai.tasks.ToDo;

public class TaskListTest {
    @Test
    public void add_newTask_success() {
        Task task = new ToDo("test");
        Task temp = new ToDo("temp");
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.add(task);
        assertEquals(taskList.getTask(0), task);
        assertEquals(taskList.size(), 1);
        assertFalse(taskList.isEmpty());

        taskList.add(temp);
        assertEquals(taskList.getTask(1), temp);
        assertEquals(taskList.size(), 2);
        assertFalse(taskList.isEmpty());

    }

    @Test
    public void remove_deleteTask_success() {
        Task task = new ToDo("test");
        Task temp = new ToDo("temp");
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.add(task);
        taskList.remove(0);
        assertEquals(taskList.size(), 0);
        assertTrue(taskList.isEmpty());


        taskList.add(task);
        taskList.add(temp);

        taskList.remove(0);
        assertEquals(taskList.getTask(0), temp);
        assertEquals(taskList.size(), 1);
        assertFalse(taskList.isEmpty());

        taskList.add(task);
        taskList.remove(1);
        assertEquals(taskList.getTask(0), temp);
        assertEquals(taskList.size(), 1);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void isEmpty_newTaskList_true() {
        assertTrue(new TaskList(new ArrayList<>()).isEmpty());
    }

    @Test
    public void size_newTaskList_true() {
        assertEquals(new TaskList(new ArrayList<>()).size(), 0);
    }
}
