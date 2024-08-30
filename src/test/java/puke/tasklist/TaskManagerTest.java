package puke.tasklist;

import org.junit.jupiter.api.Test;
import puke.exceptions.PukeException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.tasks.Task;
import puke.tasks.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the TaskManager class.
 */
public class TaskManagerTest {
    ArrayList<Task> tasks = new ArrayList<>();
    Task task1 = new Todo("Task #1", false);
    Task task2 = new Todo("Task #2", false);
    Task task3 = new Todo("Task #3", false);

    /**
     * Initializes the task list with predefined tasks.
     */
    private void initializeTasks() {
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    /**
     * Tests the addition of a new task to the TaskManager.
     *
     * @throws TaskNumberOutOfBoundsException if the task number is out of bounds
     */
    @Test
    public void addTaskTest() throws TaskNumberOutOfBoundsException {
        initializeTasks();
        Task newTask = new Todo("This is a new task", false);
        TaskManager taskManager = new TaskManager(tasks);
        try {
            taskManager.addTask("todo", newTask.getDescription());
            assertEquals(newTask, taskManager.getTask(3));
        } catch (PukeException e) {
            fail("Failed to add task");
        }
    }

    /**
     * Tests the deletion of a task from the TaskManager.
     */
    @Test
    public void deleteTaskTest() {
        initializeTasks();
        TaskManager taskManager = new TaskManager(tasks);
        try {
            taskManager.deleteTask(1);
            assertEquals(task3, taskManager.getTask(1));
        } catch (PukeException e) {
            fail("Failed to delete task");
        }
    }

    /**
     * Tests the handling of invalid task indices in the TaskManager.
     */
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
