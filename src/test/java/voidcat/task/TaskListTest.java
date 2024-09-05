package voidcat.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Test add todo");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.deleteTask(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Test delete todo");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }
}

