package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Sample Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount(), "Task list size should be 1 after adding a task.");

        try {
            assertEquals(task, taskList.getTask(0), "The added task should be retrievable.");
        } catch (MeowException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Sample Task");
        taskList.addTask(task);
        taskList.addTask(task);
        taskList.addTask(task);
        try {
            taskList.deleteTask(2);
            assertEquals(2, taskList.getTaskCount(), "Task list size should be 1 after adding a task.");
        } catch (MeowException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDeleteEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(MeowException.class, () -> {
            taskList.deleteTask(2);
        }, "Cannot delete from an empty list");
    }

    @Test
    public void testDeleteInvalidNumber() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Sample Task");
        taskList.addTask(task);
        assertThrows(MeowException.class, () -> {
            taskList.deleteTask(2);
        }, "Only 1 task in the list");
    }
}