package seanbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seanbot.Tasks.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Test task");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Test task 1");
        Task task2 = new Task("Test task 2");
        Task task3 = new Task("Test task 3");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.deleteTask(0);

        assertEquals(2, taskList.size());
    }
}