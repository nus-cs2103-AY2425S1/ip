package hana.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hana.task.TaskList;
import hana.task.Task;
import hana.task.ToDo;

public class TaskListTest {
    private TaskList taskList;

    @Test
    public void addTask_addsTaskCorrectly() {
        taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void deleteTask_removesTaskCorrectly() {
        taskList = new TaskList();
        Task task1 = new ToDo("Test task 1");
        Task task2 = new ToDo("Test task 2");
        taskList.add(task1);
        taskList.add(task2);
        taskList.remove(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }
}

