package bing.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Task("Task 1");
        task2 = new Task("Task 2");
    }

    @Test
    public void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.get(0));
    }

    @Test
    public void testDeleteTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }
}
