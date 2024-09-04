package puke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import puke.exceptions.PukeException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.task.Task;
import puke.task.Todo;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Task task1 = new Todo("Task #1", false);
    private Task task2 = new Todo("Task #2", false);
    private Task task3 = new Todo("Task #3", false);

    /**
     * Initializes the task list with predefined tasks.
     */
    private void initializeTasks() {
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    /**
     * Tests the addition of a new task to the TaskList.
     *
     * @throws TaskNumberOutOfBoundsException if the task number is out of bounds
     */
    @Test
    public void addTaskTest() throws TaskNumberOutOfBoundsException {
        initializeTasks();
        Task newTask = new Todo("This is a new task", false);
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.addTask("todo", newTask.getDescription());
            assertEquals(newTask, taskList.getTask(3));
        } catch (PukeException e) {
            fail("Failed to add task");
        }
    }

    /**
     * Tests the deletion of a task from the TaskList.
     */
    @Test
    public void deleteTaskTest() {
        initializeTasks();
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.deleteTask(1);
            assertEquals(task3, taskList.getTask(1));
        } catch (PukeException e) {
            fail("Failed to delete task");
        }
    }

    /**
     * Tests the handling of invalid task indices in the TaskList.
     */
    @Test
    public void invalidTaskIndexTest() {
        initializeTasks();
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.getTask(100);
        } catch (PukeException e) {
            assertEquals("OOPS!!! The task number " + 100 + " is out of bounds.", e.getMessage());
        }
    }
}
