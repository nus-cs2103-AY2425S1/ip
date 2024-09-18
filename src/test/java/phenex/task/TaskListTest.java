package phenex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import phenex.exception.PhenexException;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests that a phenex exception is thrown for inputting an invalid index when marking task.
     */
    @Test
    public void invalid_index_phenexExceptionThrown() {
        TaskList taskList = new TaskList();
        ToDo toDoTask = new ToDo("task Test");
        taskList.addTask(toDoTask);

        try {
            taskList.markTaskCompleted(-1);
            fail();
        } catch (PhenexException e) {
            assertEquals("\t Invalid input, no such mission!", e.getMessage());
        }
    }

    /**
     * Tests that adding a valid task works properly.
     */
    @Test
    public void add_task_success() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test");
        taskList.addTask(toDo);

        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests that taskList correctly finds a contained task.
     */
    @Test
    public void find_task_success() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test");
        taskList.addTask(toDo);
        assertEquals(true, taskList.containsTask(toDo));
    }

    /**
     * Tests that taskList correctly detects a task is not contained.
     */
    @Test
    public void find_task_failure() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test");
        taskList.addTask(toDo);
        ToDo task = new ToDo("Not found");
        assertEquals(false, taskList.containsTask(task));
    }
}
