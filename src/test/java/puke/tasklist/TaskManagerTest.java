package puke.tasklist;

import org.junit.jupiter.api.Test;
import puke.exceptions.PukeException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.tasks.Task;
import puke.tasks.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskManagerTest {
    ArrayList<Task> tasks = new ArrayList<>();
    Task task1 = new Todo("Task #1", false);
    Task task2 = new Todo("Task #2", false);
    Task task3 = new Todo("Task #3", false);

    private void initializeTasks() {
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    @Test
    public void addTaskTest() throws TaskNumberOutOfBoundsException {
        initializeTasks();
        Task newTask = new Todo("This is a new task", false);
        TaskManager taskManager = new TaskManager(tasks);
        try {
            taskManager.addTask("todo", newTask.getDescription());
            assertEquals(newTask, taskManager.getTask(3));
        } catch (PukeException e) {
            fail("Fail to add task");
        }
    }

    @Test
    public void deleteTaskTest() {
        initializeTasks();
        TaskManager taskManager = new TaskManager(tasks);
        try {
            taskManager.deleteTask(1);
            assertEquals(task3, taskManager.getTask(1));
        } catch (PukeException e) {
            fail("Fail to delete task");
        }
    }

    @Test
    public void invalidTaskIndexTest() {
        initializeTasks();
        TaskManager taskManager = new TaskManager(tasks);
        try {
            taskManager.getTask(100);
        } catch (PukeException e) {
            assertEquals("OOPS!!! The task number " + 100 + " is out of bounds.", e.getMessage());
        }
    }
}
