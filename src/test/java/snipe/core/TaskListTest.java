package snipe.core;

import org.junit.jupiter.api.Test;
import snipe.exception.SnipeException;
import snipe.task.Deadline;
import snipe.task.Event;
import snipe.task.Task;
import snipe.task.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void addTaskTest() throws SnipeException {
        Task taskOne = new ToDo("todo read book");
        Task taskTwo = new Event("Career Fair", "2024-08-27 0800", "2024-08-29 1700");
        Task taskThree = new Deadline("deadline finish work", "2024-09-23 1800");

        TaskList dummyTaskList = new TaskList();

        dummyTaskList.addTask(taskOne);
        dummyTaskList.addTask(taskTwo);
        dummyTaskList.addTask(taskThree);

        assertEquals(3, dummyTaskList.size());
        assertEquals("Career Fair", dummyTaskList.getTask(1).getDescription());
    }
    @Test
    public void deleteTaskTest() throws SnipeException {
        ArrayList<Task> dummyTasks = new ArrayList<Task>();

        dummyTasks.add(new ToDo("todo read book"));
        dummyTasks.add(new Event("Career Fair", "2024-08-27 0800", "2024-08-29 1700"));
        dummyTasks.add(new Deadline("deadline finish work", "2024-09-23 1800"));

        TaskList dummyTaskList = new TaskList(dummyTasks);
        dummyTaskList.deleteTask(1);

        assertEquals(2, dummyTaskList.size());
        assertEquals("deadline finish work", dummyTaskList.getTask(1).getDescription());
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        Task task = new ToDo("Task 1");
        TaskList taskList = new TaskList();
        taskList.addTask(task);

        // Assert that SnipeException is thrown when an invalid index is provided
        assertThrows(SnipeException.class, () -> {
            taskList.deleteTask(2);
        });
    }
}
