package lama;

import lama.task.Task;
import lama.task.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testInstantiateEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void testInstantiateGivenTaskList() {
        ArrayList<Task> given = new ArrayList<>();
        Task todo = new Todo("Read Book");
        given.add(todo);
        TaskList taskList = new TaskList(given);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }

    @Test
    public void testSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void testAdd() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testGet() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testRemove() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
        Task taskRemoved = taskList.remove(0);
        assertEquals(task, taskRemoved);
    }

}
